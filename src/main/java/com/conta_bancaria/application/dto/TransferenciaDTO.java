package com.conta_bancaria.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record TransferenciaDTO(
        @NotBlank
        @Pattern(regexp = "\\d{5,20}", message = "Número da conta destino inválido")
        String contaDestino,

        @NotNull
        @Positive(message = "O valor deve ser maior que zero")
        BigDecimal valor
) {
}
