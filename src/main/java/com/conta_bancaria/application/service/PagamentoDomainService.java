package com.conta_bancaria.application.service;

import com.conta_bancaria.application.dto.PagamentoRequestDTO;
import com.conta_bancaria.domain.repository.PagamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PagamentoDomainService {
    private final PagamentoRepository repository;

    @PreAuthorize("hasRole('GERENTE')")
    public PagamentoRequestDTO gerarBoleto(PagamentoRequestDTO dto){

    }
}
