package com.conta_bancaria.application.dto;

import com.conta_bancaria.domain.entity.Cliente;

public record ClienteAtualizadoDTO(
        String nome,
        String cpf
) {
}
