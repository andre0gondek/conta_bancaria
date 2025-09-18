package com.conta_bancaria.application.dto;

import java.util.List;

public record ClienteResponseDTO(
    String id,
    String nome,
    String cpf,
    List<ContaResumoDTO> contas
) {
}
