package com.pos.system.modules.sales.controller;

import com.pos.system.modules.sales.dto.SaleRequestDTO;
import com.pos.system.modules.sales.entity.Sale;
import com.pos.system.modules.sales.service.SaleService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping
    public ResponseEntity<Sale> createSale(@RequestBody SaleRequestDTO request,
                                           @AuthenticationPrincipal UserDetails userDetails) {
        // We pass the username of the logged-in cashier
        Sale newSale = saleService.processSale(request, userDetails.getUsername());
        return ResponseEntity.ok(newSale);
    }
}