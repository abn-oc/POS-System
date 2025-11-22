package com.pos.system.modules.sales.dto;

import java.util.List;

public class SaleRequestDTO {
    private List<CartItemDTO> items;
    private List<PaymentDTO> payments; // <--- ADD THIS

    // --- GETTERS & SETTERS ---
    public List<CartItemDTO> getItems() { return items; }
    public void setItems(List<CartItemDTO> items) { this.items = items; }
    public List<PaymentDTO> getPayments() { return payments; }
    public void setPayments(List<PaymentDTO> payments) { this.payments = payments; }
}