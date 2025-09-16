package com.conta_bancaria.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //parecido com a MappedSuperclass, mas cria uma tabela única para todas as subclasses
@DiscriminatorColumn(
        name = "tipo",
        discriminatorType = DiscriminatorType.STRING,
        length = 20
    ) //coluna que define o tipo da conta (corrente ou poupança)
@Table(name = "contas",
        uniqueConstraints = {
           @UniqueConstraint(name = "uk_conta_numero", columnNames = "numero"),
           @UniqueConstraint(name = "uk_cliente_tipo", columnNames = "cliente_id, tipo")
       } ) //garante que um cliente não tenha mais de uma conta do mesmo tipo, e gera o tipo de conta
@SuperBuilder
@NoArgsConstructor
public abstract class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, length = 20)
    private String numero;

    @Column(nullable = false, precision = 4) //precision define a quantidade de casas decimais
   //@Positive(message = "O saldo deve ser maior ou igual a R$0,00")
    private BigDecimal saldo; //utilizado BigDecimal para valores monetários

    @Column(nullable = false)
    private Boolean ativo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", foreignKey = @ForeignKey(name = "fk_conta_cliente")) //chave estrangeira para o cliente
    private Cliente cliente;
}
