package com.pos.system.modules.sales.repository;

import com.pos.system.modules.sales.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}