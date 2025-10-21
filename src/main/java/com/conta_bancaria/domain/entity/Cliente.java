    package com.conta_bancaria.domain.entity;

    import jakarta.persistence.*;
    import jakarta.validation.constraints.NotBlank;
    import lombok.*;
    import lombok.experimental.SuperBuilder;

    import java.util.ArrayList;
    import java.util.List;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @SuperBuilder
    @Entity
    @Table(name = "clientes",
            uniqueConstraints = {
                    @UniqueConstraint(name = "uk_cliente_cpf", columnNames = "cpf")
            })
    public class Cliente extends Usuario{

        /* @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        private String id;

        @Column(nullable = false, length = 120)
        private String nome;

        @Column(nullable = false, length = 11)
        private String cpf; */

        @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
        @Builder.Default
        private List<Conta> contas = new ArrayList<>();

       /* @Column(nullable = false)
        private Boolean ativo; */
    }

