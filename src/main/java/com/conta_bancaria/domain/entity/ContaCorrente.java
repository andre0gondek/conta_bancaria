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
}

