package com.conta_bancaria.application.service;

import com.conta_bancaria.application.dto.PagamentoRequestDTO;
import com.conta_bancaria.application.dto.PagamentoResponseDTO;
import com.conta_bancaria.domain.entity.Pagamento;
import com.conta_bancaria.domain.repository.PagamentoRepository;
import org.springframework.security.access.prepost.PreAuthorize;

public class PagamentoAppService {
    private static PagamentoRepository repository;

    public PagamentoResponseDTO pagarBoleto(PagamentoRequestDTO dto){

        return PagamentoResponseDTO.fromEntity(repository.save(new Pagamento()));
    }
}
