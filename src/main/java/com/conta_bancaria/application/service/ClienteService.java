package com.conta_bancaria.application.service;

import com.conta_bancaria.application.dto.ClienteRegistroDTO;
import com.conta_bancaria.application.dto.ClienteResponseDTO;
import com.conta_bancaria.domain.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

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

        var novaConta = dto.contaDTO().toEntity(cliente);

        boolean hasConta = contas.stream()
                .anyMatch(conta -> conta.getClass().equals(dto.contaDTO().getClass() && conta.isAtivo()));

        return
    }

}
