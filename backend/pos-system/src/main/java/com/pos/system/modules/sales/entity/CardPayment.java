package com.pos.system.modules.sales.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CARD")
public class CardPayment extends Payment {

    private String cardLastFourDigits;
    private String authCode; // From payment gateway

    @Override
    public String getMethodName() {
        return "CARD";
    }

    // --- GETTERS & SETTERS ---
    public String getCardLastFourDigits() { return cardLastFourDigits; }
    public void setCardLastFourDigits(String cardLastFourDigits) { this.cardLastFourDigits = cardLastFourDigits; }
    public String getAuthCode() { return authCode; }
    public void setAuthCode(String authCode) { this.authCode = authCode; }
}