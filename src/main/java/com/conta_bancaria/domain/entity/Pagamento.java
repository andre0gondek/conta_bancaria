package com.conta_bancaria.domain.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @Column(nullable = false)
    private Conta conta;

    private String boleto;

    @Column(nullable = false, precision = 19, scale = 2) // Ajuste na precisão para valores monetários
    protected BigDecimal valorPago;

    @Column(nullable = false)
    private boolean dataPagamento;
    @Column
    private String status;

    @ManyToMany
    @Column
    private Taxa taxas; // Necessário para a constraint única
}
