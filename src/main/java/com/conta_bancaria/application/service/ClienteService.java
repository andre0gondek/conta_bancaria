package com.conta_bancaria.application.service;

import com.conta_bancaria.application.dto.ClienteDTO;
import com.conta_bancaria.domain.entity.Cliente;
import com.conta_bancaria.domain.entity.Conta;
import com.conta_bancaria.domain.repository.ClienteRepository;
import com.conta_bancaria.domain.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    ContaRepository contaRepository;

    @Transactional(readOnly = true)
    public List<ClienteDTO> listarClientes() {
        return clienteRepository.findAll()
                .stream()
                .map(ClienteDTO::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public ClienteDTO buscarClientesPorId(String id) {
        return clienteRepository.findById(id)
                .map(ClienteDTO::fromEntity)
                .orElse(null);
    }

    public ClienteDTO salvarCliente(ClienteDTO dto) {
        List<Conta> contas = new ArrayList<>();
        if(dto.idContas() != null) {
            contas = contaRepository.findAllById(dto.idContas());
        }
        Cliente entidade = dto.toEntity(contas);
        Cliente salvo = clienteRepository.save(entidade);
        return ClienteDTO.fromEntity(salvo);
    }

    public ClienteDTO atualizarCliente(String id, ClienteDTO dto) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(id);
        if(clienteOpt.isEmpty()) return null;

        Cliente existente = clienteOpt.get();
        existente.setNome(dto.nome());
        existente.setCpf(dto.cpf());

        if(dto.idContas() != null){
            List<Conta> contas = contaRepository.findAllById(dto.idContas());
            existente.setContas(contas);
        }

        Cliente atualizado = clienteRepository.save(existente);
        return ClienteDTO.fromEntity(atualizado);
    }

    public void deletarCliente(String id) {
        clienteRepository.deleteById(id);
    }
}
