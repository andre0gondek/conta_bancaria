package com.conta_bancaria.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_conta", discriminatorType = DiscriminatorType.STRING, length = 20)
@Table(name = "conta",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_conta_numero", columnNames = "numero"),
                @UniqueConstraint(name = "uk_cliente_tipo", columnNames = {"cliente_id", "tipo_conta"})
        })
@Data
@SuperBuilder
@NoArgsConstructor
public abstract class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, length = 20)
    private String numero;

    @Column(nullable = false, precision = 19, scale = 2) // Ajuste na precisão para valores monetários
    protected BigDecimal saldo;

    @Column(nullable = false)
    private boolean ativa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", foreignKey = @ForeignKey(name = "fk_conta_cliente"))
    private Cliente cliente;

    @Column(name = "tipo_conta", insertable = false, updatable = false)
    private String tipoConta; // Necessário para a constraint única

    public abstract String getTipo();

    public void sacar(BigDecimal valor) {
        validarValorMaiorQueZero(valor);

        if (valor.compareTo(saldo) > 0) {
            throw new IllegalArgumentException("Saldo insuficiente para saque.");
        }

        saldo = saldo.subtract(valor);
    }

    public void depositar(BigDecimal valor) {
        validarValorMaiorQueZero(valor);
        saldo = saldo.add(valor);
    }

    public void transferir(BigDecimal valor, Conta contaDestino) {
        if (this.id.equals(contaDestino.getId())) {
            throw new IllegalArgumentException("Não é possível transferir para a mesma conta.");
        }

        this.sacar(valor);
        contaDestino.depositar(valor);
    }

    protected static void validarValorMaiorQueZero(BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor não pode ser negativo");
        }
    }
}

