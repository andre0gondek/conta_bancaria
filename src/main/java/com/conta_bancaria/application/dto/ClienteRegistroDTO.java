package com.conta_bancaria.application.dto;

import com.conta_bancaria.domain.entity.Cliente;
import com.conta_bancaria.domain.entity.Conta;
import com.conta_bancaria.domain.enums.Role;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;

public record ClienteRegistroDTO(
        @NotBlank(message = "Digite um nome para o cliente.")
        @Size(min = 3, max = 100)
        String nome,

        @NotBlank(message = "O CPF não pode estar vazio.")
        @Pattern(regexp = "\\d{11}", message = "O CPF deve conter exatamente 11 dígitos numéricos")
        String cpf,

        @NotNull(message = "Deve haver pelo menos uma conta.")
        @Valid
        ContaResumoDTO contas,

        String email,
        String senha
) {
    public Cliente toEntity() {
        return Cliente.builder()
                .ativo(true)
                .nome(this.nome)
                .cpf(this.cpf)
                .contas(new ArrayList<Conta>())
                .email(this.email)
                .senha(this.senha)
                .role(Role.CLIENTE)
                .build();
    }
}
