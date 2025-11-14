package com.conta_bancaria.application.service;

import com.conta_bancaria.application.dto.PagamentoRequestDTO;
import com.conta_bancaria.application.dto.PagamentoResponseDTO;
import com.conta_bancaria.domain.repository.PagamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PagamentoDomainService {
    private static PagamentoRepository repository;
    @PreAuthorize("hasRole('GERENTE')")
    public PagamentoResponseDTO gerarPagamento(PagamentoRequestDTO dto){
        var pagamento = repository.findByBoleto(dto.boleto()).orElseGet(
                () -> repository.save(dto.toEntity())
        );
        return PagamentoResponseDTO.fromEntity(repository.save(pagamento));
    }
}
