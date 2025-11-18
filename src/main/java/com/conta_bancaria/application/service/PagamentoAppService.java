package com.conta_bancaria.application.service;

import com.conta_bancaria.application.dto.PagamentoRequestDTO;
import com.conta_bancaria.application.dto.PagamentoResponseDTO;
import com.conta_bancaria.domain.PagamentoDomainService;
import com.conta_bancaria.domain.entity.Conta;
import com.conta_bancaria.domain.entity.Pagamento;
import com.conta_bancaria.domain.exception.EntidadeNaoEncontradaException;
import com.conta_bancaria.domain.repository.ContaRepository;
import com.conta_bancaria.domain.repository.PagamentoRepository;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public class PagamentoAppService {

    private static PagamentoRepository repository;
    private static ContaRepository contaRepository;

    private Pagamento buscarPagamentoPorBoleto(String boleto) {
        var pagamento = repository.findByBoleto(boleto).orElseThrow(
                () -> new EntidadeNaoEncontradaException("pagamento")
        );
        return pagamento;
    }

    @PreAuthorize("hasRole('GERENTE')")
    public PagamentoResponseDTO processarPagamento(PagamentoRequestDTO dto, boolean respostaValidaMQTT) {
        Conta conta = contaRepository.findByNumeroAndAtivaTrue(dto.contaNumero())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Conta não encontrada"));

        Pagamento pagamento = dto.toEntity(conta);

        PagamentoDomainService.validarPagamento(pagamento);

        PagamentoDomainService.calcularTaxa(pagamento);

        PagamentoDomainService.definirStatus(pagamento, respostaValidaMQTT);

        Pagamento pagamentoSalvo = repository.save(pagamento);

        return PagamentoResponseDTO.fromEntity(pagamentoSalvo);
    }

    @PreAuthorize("hasAnyRole('GERENTE')")
    public PagamentoResponseDTO verPagamento(String boleto){
        var pagamento = buscarPagamentoPorBoleto(boleto);
        return PagamentoResponseDTO.fromEntity(pagamento);
    }

    @PreAuthorize("hasRole('GERENTE')")
    public List<PagamentoResponseDTO> listarPagamentos(){
        return repository.findAll().stream()
                .map(PagamentoResponseDTO::fromEntity)
                .toList();
    }

    @PreAuthorize("hasRole('CLIENTE')")
    public List<PagamentoResponseDTO> verPagamentoPorConta(String contaNumero){
        Conta conta = contaRepository.findByNumeroAndAtivaTrue(contaNumero)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Conta não encontrada"));
        return repository.findByConta(conta).stream()
                .map(PagamentoResponseDTO::fromEntity)
                .toList();
    }

}
