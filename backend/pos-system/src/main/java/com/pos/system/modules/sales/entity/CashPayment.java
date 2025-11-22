package com.pos.system.modules.sales.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("CASH")
public class CashPayment extends Payment {

    private BigDecimal cashTendered; // Amount customer handed over
    private BigDecimal changeGiven;

    @Override
    public String getMethodName() {
        return "CASH";
    }

    // --- GETTERS & SETTERS ---
    public BigDecimal getCashTendered() { return cashTendered; }
    public void setCashTendered(BigDecimal cashTendered) { this.cashTendered = cashTendered; }
    public BigDecimal getChangeGiven() { return changeGiven; }
    public void setChangeGiven(BigDecimal changeGiven) { this.changeGiven = changeGiven; }
}