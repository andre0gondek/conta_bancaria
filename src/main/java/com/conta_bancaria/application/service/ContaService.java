package com.conta_bancaria.application.service;

import com.conta_bancaria.application.dto.ContaAtualizadaDTO;
import com.conta_bancaria.application.dto.ContaResumoDTO;
import com.conta_bancaria.application.dto.SaqueDepositoDTO;
import com.conta_bancaria.domain.entity.Conta;
import com.conta_bancaria.domain.entity.ContaCorrente;
import com.conta_bancaria.domain.entity.ContaPoupanca;
import com.conta_bancaria.domain.repository.ContaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ContaService {

    private final ContaRepository repository;

    @Transactional(readOnly = true)
    public List<ContaResumoDTO> exibirContas() {
        return repository.findAtiva().stream()
                .map(ContaResumoDTO::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public ContaResumoDTO exibirContasPorNumero(String num) {
        var conta = repository.findByNumAndAtivoTrue(num).orElseThrow(
                () -> new RuntimeException("Conta não encontrada")
        );
        return ContaResumoDTO.fromEntity(conta);
    }


    public ContaResumoDTO atualizarConta(String numero, ContaAtualizadaDTO dto) {
        Conta c = repository.findByNumAndAtivoTrue(numero)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        if (c instanceof ContaPoupanca contaPoupanca) {
            contaPoupanca.setRendimento(dto.rendimento());
        } else if (c instanceof ContaCorrente contaCorrente) {
            contaCorrente.setLimite(dto.limite());
            contaCorrente.setTaxa(dto.taxa());
        } else throw new RuntimeException("Tipo de conta inválido. Tente novamente.");
        c.setSaldo(dto.saldo());

        return ContaResumoDTO.fromEntity(repository.save(c));
    }

    public void deletarConta(String numero) {
        Conta c = repository.findByNumAndAtivoTrue(numero)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));
        c.setAtiva(false);
        repository.save(c);
    }

    public ContaResumoDTO sacar(String numero, SaqueDepositoDTO dto) {
        Conta c = repository.findByNumAndAtivoTrue(numero)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        c.sacar(dto.valor());
        return ContaResumoDTO.fromEntity(repository.save(c));
    }
}
