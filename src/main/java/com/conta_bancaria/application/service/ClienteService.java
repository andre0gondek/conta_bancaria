package com.conta_bancaria.application.service;

import com.conta_bancaria.application.dto.ClienteRegistroDTO;
import com.conta_bancaria.application.dto.ClienteResponseDTO;
import com.conta_bancaria.domain.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteResponseDTO registrarCliente(ClienteRegistroDTO dto) {
        var cliente = clienteRepository.findByCpfAndAtivoTrue(dto.cpf()).orElseGet(
                () -> clienteRepository.save(dto.toEntity())

        );

        var contas = cliente.getContas();
        var novaConta = dto.contas().toEntity(cliente);

        boolean hasConta = contas.stream().anyMatch(
                conta -> conta.getClass().equals(novaConta.getClass()) && conta.isAtiva()
        );

        if (hasConta)
            throw new IllegalArgumentException("Cliente já possui uma conta do tipo " + novaConta.getClass().getSimpleName());

        cliente.getContas().add(novaConta);

        return ClienteResponseDTO.fromEntity(clienteRepository.save(cliente));
    }

    public List<ClienteResponseDTO> exibirClientesAtivos() {
        return clienteRepository.findAllByAtivoTrue().stream()
                .map(ClienteResponseDTO::fromEntity)
                .toList();
    }

    public ClienteResponseDTO buscarClientePorCpf(String cpf) {
        var cliente = clienteRepository.findByCpfAndAtivoTrue(cpf).orElseThrow(
                () -> new RuntimeException("Cliente não encontrado")
        );
        return ClienteResponseDTO.fromEntity(cliente);
    }

   /*
   Minha versão:
   public List<ClienteResponseDTO> buscarClientePorCpf(String cpf) {
        return clienteRepository.findByCpfAndAtivoTrue(cpf).stream()
                .map(ClienteResponseDTO::fromEntity)
                .toList();
    }*/

    public void atualizarCliente(String cpf, ClienteRegistroDTO dto) {
        var cliente = clienteRepository.findByCpfAndAtivoTrue(cpf)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
        var contas = cliente.getContas();
        var novaConta = dto.contas().toEntity(cliente);

        boolean hasConta = contas.stream().anyMatch(
                conta -> conta.getClass().equals(novaConta.getClass()) && conta.isAtiva()
        );

        if (hasConta)
            throw new IllegalArgumentException("Cliente já possui uma conta do tipo " + novaConta.getClass().getSimpleName());

        cliente.getContas().add(novaConta);


        cliente.setNome(dto.nome());
        cliente.setCpf(dto.cpf());
        cliente.setContas(dto.toEntity().getContas());

        ClienteResponseDTO.fromEntity(clienteRepository.save(cliente));
    }

    public void deletarCliente(String cpf) {
        clienteRepository.deleteById(cpf);
    }
}
