package com.conta_bancaria.domain.entity;
import jakarta.persistence.Column;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("CORRENTE")
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class ContaCorrente extends Conta {

    @Column(precision = 10, scale = 2) // Ajuste na precisão
    private BigDecimal limite;

    @Column(precision = 10, scale = 2) // Ajuste na precisão
    private BigDecimal taxa;

    @Override
    public String getTipo() {
        return "CORRENTE";
    }

    @Override
    public void sacar(BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Valor inválido para saque.");
        }
        BigDecimal custoSaque = valor.multiply(taxa);
        BigDecimal total = valor.add(custoSaque);
        if (saldo.add(limite).compareTo(total) < 0){
            throw new IllegalArgumentException("Limite insuficiente para saque.");
        }
        setSaldo(getSaldo().subtract(total));
    }
}

