package com.pos.system.modules.sales.dto;

import java.util.List;

public class SaleRequestDTO {
    private List<CartItemDTO> items;

    // Manual Getters/Setters
    public List<CartItemDTO> getItems() { return items; }
    public void setItems(List<CartItemDTO> items) { this.items = items; }
}