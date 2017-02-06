package br.com.casadocodigo.shop.models;

import java.math.BigDecimal;

public class PaymentData {
    private final BigDecimal value;

    public PaymentData(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }
}
