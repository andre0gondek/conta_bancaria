package com.conta_bancaria.domain.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private int numero;

    @Positive(message = "O saldo deve ser maior ou igual a R$0,00")
    private double saldo;

    private String tipo;
}
