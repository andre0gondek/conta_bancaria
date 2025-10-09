package com.conta_bancaria.application.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record SaqueDepositoDTO(
        @NotNull(message = "O saldo não pode estar nulo!")
        BigDecimal valor
) {
}
