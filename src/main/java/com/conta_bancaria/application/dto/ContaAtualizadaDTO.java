package com.conta_bancaria.application.dto;

import java.math.BigDecimal;

public record ContaAtualizadaDTO(
        BigDecimal saldo,
        BigDecimal limite,
        BigDecimal rendimento,
        BigDecimal taxa
) {
}
