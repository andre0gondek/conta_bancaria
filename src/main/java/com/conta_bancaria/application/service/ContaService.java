package com.conta_bancaria.application.service;

import com.conta_bancaria.application.dto.ClienteResponseDTO;
import com.conta_bancaria.application.dto.ContaResumoDTO;
import com.conta_bancaria.domain.entity.Conta;
import com.conta_bancaria.domain.repository.ContaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ContaService {

   private final ContaRepository repository;

    public List<ContaResumoDTO> exibirContas() {
        return repository.findAtiva().stream()
                .map(ContaResumoDTO::fromEntity)
                .toList();
    }

    public ContaResumoDTO exibirContasPorNumero(String num) {
        var conta = repository.findByNum(num).orElseThrow(
                () -> new RuntimeException("Conta não encontrada")
        );
        return ContaResumoDTO.fromEntity(conta);
    }
}
