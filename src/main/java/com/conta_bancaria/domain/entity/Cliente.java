package com.conta_bancaria.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String nome;

    @NotNull(message = "O CPF não pode estar vazio.")
    @Column(nullable = false, length = 11)
    private Long cpf;

    @OneToMany(mappedBy = "cliente")
    private List<Conta> contas = new ArrayList<>();
}
