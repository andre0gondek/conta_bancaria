package com.conta_bancaria.application.dto;

import com.conta_bancaria.domain.entity.Cliente;
import com.conta_bancaria.domain.entity.Conta;
import com.conta_bancaria.domain.entity.ContaCorrente;
import com.conta_bancaria.domain.entity.ContaPoupanca;

import java.math.BigDecimal;

public record ContaResumoDTO(
        String numero,
        String tipo,
        BigDecimal saldo
) {
    public Conta toEntity(Cliente cliente) {
        if ("CORRENTE".equalsIgnoreCase(this.tipo)) {
            return ContaCorrente.builder()
                    .cliente(cliente)
                    .numero(numero)
                    .saldo(saldo)
                    .ativa(true)
                    .build();
        } else if ("POUPANCA".equalsIgnoreCase(this.tipo)) {
            return ContaPoupanca.builder()
                    .cliente(cliente)
                    .numero(numero)
                    .saldo(saldo)
                    .ativa(true)
                    .build();
        }
        return null;
    }
}
