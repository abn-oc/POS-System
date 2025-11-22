package com.pos.system.modules.sales.dto;

import java.math.BigDecimal;

public class PaymentDTO {
    private String type; // "CASH" or "CARD"
    private BigDecimal amount;

    // Optional fields
    private BigDecimal cashTendered;
    private String cardLastFour;

    // --- GETTERS & SETTERS ---
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public BigDecimal getCashTendered() { return cashTendered; }
    public void setCashTendered(BigDecimal cashTendered) { this.cashTendered = cashTendered; }
    public String getCardLastFour() { return cardLastFour; }
    public void setCardLastFour(String cardLastFour) { this.cardLastFour = cardLastFour; }
}