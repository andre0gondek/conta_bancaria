package com.conta_bancaria.domain.entity;

import com.conta_bancaria.domain.enums.StatusPagamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
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
    private String dataPagamento;
    @Column
    private StatusPagamento status;

    @ManyToMany
    @Column
    private List<Taxa> taxas;
}
