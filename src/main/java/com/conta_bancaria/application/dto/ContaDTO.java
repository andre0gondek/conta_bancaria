package com.conta_bancaria.application.dto;

import java.math.BigDecimal;

public record ContaDTO(
    String numero,
    String tipo,
    BigDecimal saldo
) {
}
