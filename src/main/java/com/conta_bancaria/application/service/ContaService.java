package com.conta_bancaria.application.service;

import com.conta_bancaria.application.dto.ContaAtualizadaDTO;
import com.conta_bancaria.application.dto.ContaResumoDTO;
import com.conta_bancaria.application.dto.SaqueDepositoDTO;
import com.conta_bancaria.application.dto.TransferenciaDTO;
import com.conta_bancaria.domain.entity.Conta;
import com.conta_bancaria.domain.entity.ContaCorrente;
import com.conta_bancaria.domain.entity.ContaPoupanca;
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

    private Conta buscarConta(String numero) {
        return repository.findByNumeroAndAtivaTrue(numero)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));
    }

    @Transactional(readOnly = true)
    public List<ContaResumoDTO> exibirContas() {
        return repository.findAllByAtivaTrue().stream()
                .map(ContaResumoDTO::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public ContaResumoDTO exibirContasPorNumero(String numero) {
        Conta c = buscarConta(numero);
        return ContaResumoDTO.fromEntity(c);
    }


    public ContaResumoDTO atualizarConta(String numero, ContaAtualizadaDTO dto) {
        Conta c = buscarConta(numero);

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
        Conta c = buscarConta(numero);
        c.setAtiva(false);
        repository.save(c);
    }

    public ContaResumoDTO sacar(String numero, SaqueDepositoDTO dto) {
        Conta c = buscarConta(numero);

        c.sacar(dto.valor());
        return ContaResumoDTO.fromEntity(repository.save(c));
    }

    public ContaResumoDTO depositar(String numero, SaqueDepositoDTO dto) {
        Conta conta = buscarConta(numero);

        conta.depositar(dto.valor());
        return ContaResumoDTO.fromEntity(repository.save(conta));
    }

    public ContaResumoDTO transferir(String numero, TransferenciaDTO dto) {
        Conta contaOrigem = buscarConta(numero);
        Conta contaDestino = buscarConta(dto.contaDestino());

        contaOrigem.transferir(dto.valor(), contaDestino);

        repository.save(contaDestino);
        return ContaResumoDTO.fromEntity(repository.save(contaOrigem));
    }
}
