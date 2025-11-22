package com.pos.system.modules.sales.service;

import com.pos.system.modules.auth.entity.User;
import com.pos.system.modules.auth.repository.UserRepository;
import com.pos.system.modules.sales.dto.SaleRequestDTO;
import com.pos.system.modules.inventory.entity.Product;
import com.pos.system.modules.inventory.repository.ProductRepository;
import com.pos.system.modules.sales.dto.CartItemDTO;
import com.pos.system.modules.sales.dto.PaymentDTO;
import com.pos.system.modules.sales.entity.CardPayment;
import com.pos.system.modules.sales.entity.CashPayment;
import com.pos.system.modules.sales.entity.Payment;
import com.pos.system.modules.sales.entity.Sale;
import com.pos.system.modules.sales.entity.SaleItem;
import com.pos.system.modules.sales.repository.SaleRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.ArrayList;
@Service
public class SaleService {

    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public SaleService(SaleRepository saleRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.saleRepository = saleRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Transactional // <--- CRITICAL: If any line fails, database rolls back
    public Sale processSale(SaleRequestDTO request, String username) {
        Sale sale = new Sale();
        sale.setInvoiceNumber(UUID.randomUUID().toString().substring(0, 8)); // Simple invoice #
        sale.setStatus("COMPLETED");

        // Get Cashier
        User cashier = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Cashier not found"));
        sale.setCashier(cashier);

        BigDecimal totalAmount = BigDecimal.ZERO;

        // Loop through cart items
        for (CartItemDTO itemDTO : request.getItems()) {
            Product product = productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found: " + itemDTO.getProductId()));

            // 1. Check Stock (UC-33)
            if (product.getStockQuantity() < itemDTO.getQuantity()) {
                throw new RuntimeException("Not enough stock for: " + product.getName());
            }

            // 2. Deduct Stock (UC-43)
            product.setStockQuantity(product.getStockQuantity() - itemDTO.getQuantity());
            productRepository.save(product);

            // 3. Create Sale Item
            SaleItem saleItem = new SaleItem();
            saleItem.setProduct(product);
            saleItem.setQuantity(itemDTO.getQuantity());
            saleItem.setUnitPrice(product.getPrice());
            saleItem.setSubtotal(product.getPrice().multiply(BigDecimal.valueOf(itemDTO.getQuantity())));
            saleItem.setSale(sale); // Link back to parent

            sale.getItems().add(saleItem);
            totalAmount = totalAmount.add(saleItem.getSubtotal());
        }

        sale.setTotalAmount(totalAmount);

        // --- PAYMENT LOGIC (New) ---
        BigDecimal totalPaid = BigDecimal.ZERO;

        // If payments are provided
        if (request.getPayments() != null) {
            // Initialize list if null in entity
            if (sale.getPayments() == null) sale.setPayments(new ArrayList<>());

            for (PaymentDTO pDto : request.getPayments()) {
                Payment payment;

                if ("CASH".equalsIgnoreCase(pDto.getType())) {
                    CashPayment cp = new CashPayment();
                    cp.setCashTendered(pDto.getCashTendered());
                    // Calculate change: Tendered - Amount
                    if (pDto.getCashTendered() != null) {
                        cp.setChangeGiven(pDto.getCashTendered().subtract(pDto.getAmount()));
                    }
                    payment = cp;
                } else {
                    CardPayment cp = new CardPayment();
                    cp.setCardLastFourDigits(pDto.getCardLastFour());
                    payment = cp;
                }

                payment.setAmount(pDto.getAmount());
                payment.setSale(sale); // Link to parent
                sale.getPayments().add(payment); // Add to list

                totalPaid = totalPaid.add(pDto.getAmount());
            }

            // Validation: Did they pay enough?
            if (totalPaid.compareTo(totalAmount) < 0) {
                throw new RuntimeException("Insufficient Payment! Total Due: " + totalAmount + ", Paid: " + totalPaid);
            }
        }

        return saleRepository.save(sale);
    }
}