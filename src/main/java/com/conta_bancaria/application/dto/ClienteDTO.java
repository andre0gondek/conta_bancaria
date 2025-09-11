package com.conta_bancaria.application.dto;

import com.conta_bancaria.domain.entity.Cliente;
import com.conta_bancaria.domain.entity.Conta;

import java.util.ArrayList;
import java.util.List;

public record ClienteDTO(
        String id,
        String nome,
        Long cpf,
        List<String> idContas
) {
    public static ClienteDTO fromEntity(Cliente cliente){
        if (cliente == null) return null;
        List<String> idContas = cliente.getContas()
                .stream()
                .map(Conta::getId)
                .toList();

        return new ClienteDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getCpf(),
                idContas
        );
    }

    public Cliente toEntity(List<Conta> contas){
        Cliente cliente = new Cliente();
        cliente.setNome(this.nome);
        cliente.setCpf(this.cpf);
        cliente.setContas(contas);
        return cliente;
    }
}
