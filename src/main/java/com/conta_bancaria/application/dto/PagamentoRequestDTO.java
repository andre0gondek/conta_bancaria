package com.conta_bancaria.application.dto;

import com.conta_bancaria.domain.entity.Conta;
import com.conta_bancaria.domain.entity.Pagamento;
import com.conta_bancaria.domain.entity.Taxa;
import com.conta_bancaria.domain.enums.StatusPagamento;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public record PagamentoRequestDTO(
        String contaNumero,
        String boleto,
        BigDecimal valorPago,
        StatusPagamento status,
        List<TaxaDTO.TaxaRequestDTO> taxas
) {
    public Pagamento toEntity(Conta conta) {
        return Pagamento.builder()
                .conta(conta)
                .boleto(this.boleto)
                .valorPago(this.valorPago)
                .status(StatusPagamento.PENDENTE)
                .taxas(new ArrayList<>())
                .build();
    }
}
