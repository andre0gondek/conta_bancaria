package com.conta_bancaria.application.dto;

import com.conta_bancaria.domain.entity.Cliente;
import com.conta_bancaria.domain.entity.Conta;

import java.util.ArrayList;
import java.util.List;

public record ClienteDTO(
    String nome,
    String cpf,
    ContaDTO conta
) {
}
