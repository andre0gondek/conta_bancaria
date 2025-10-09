package com.conta_bancaria.application.dto;

import com.conta_bancaria.domain.entity.Cliente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ClienteAtualizadoDTO(
        @NotBlank(message = "O nome não pode estar vazio")
        @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
        String nome,

        @NotBlank(message = "O CPF não pode estar vazio")
        @Pattern(regexp = "\\d{11}", message = "CPF deve conter 11 dígitos numéricos")
        String cpf
) {
}
