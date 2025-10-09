package com.conta_bancaria.application.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record ContaAtualizadaDTO(
        @NotNull
        @PositiveOrZero(message = "Saldo não pode ser negativo")
        BigDecimal saldo,

        BigDecimal limite,
        BigDecimal rendimento,
        BigDecimal taxa
) {
}
