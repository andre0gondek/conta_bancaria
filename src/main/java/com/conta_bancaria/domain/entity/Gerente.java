package com.conta_bancaria.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.conta_bancaria.domain.enums.Role;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Gerente {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    protected String id;

    @NotBlank
    @Column(nullable = false)
    protected String nome;

    @NotBlank
    @Column(nullable = false, unique = true, length = 14)
    protected String cpf; // formato "000.000.000-00" (validação pode ser ampliada)

    @Email
    @NotBlank
    @Column(nullable = false, unique = true)
    protected String email;

    @Column(nullable = false)
    protected boolean ativo = true;

    @NotBlank
    @Column(nullable = false)
    protected String senha;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    protected Role role;
}
