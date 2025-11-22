package com.pos.system.modules.inventory.repository;

import com.pos.system.modules.inventory.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // UC-28: Scan Barcode
    Optional<Product> findByBarcode(String barcode);

    // Search by name (Partial match, Case insensitive)
    List<Product> findByNameContainingIgnoreCase(String name);

    // AI-10: Notify Low Stock (Find items where stock <= reorder level)
    @Query("SELECT p FROM Product p WHERE p.stockQuantity <= p.reorderLevel")
    List<Product> findLowStockProducts();
}