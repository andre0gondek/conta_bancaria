package com.conta_bancaria.application.dto.conta_e_transferencias;

import com.conta_bancaria.domain.entity.Conta;
import com.conta_bancaria.domain.entity.Pagamento;
import com.conta_bancaria.domain.enums.StatusPagamento;
import com.conta_bancaria.domain.entity.Taxa;
import com.conta_bancaria.domain.enums.TipoPagamento;

import java.time.LocalDateTime;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public record PagamentoRequestDTO(
        String contaNumero,
        String boleto,
        BigDecimal valorPago,
        StatusPagamento status,
        TipoPagamento tipoPagamento,
        List<TaxaDTO.TaxaRequestDTO> taxas,
        String codigoAutenticacao
) {
    public Pagamento toEntity(Conta conta) {
        List<Taxa> taxasEntidade = new ArrayList<>();
        if (this.taxas != null) {
            taxasEntidade = this.taxas.stream().map(t -> Taxa.builder()
                    .descricao(t.descricao())
                    .percentual(t.percentual())
                    .valorFixo(t.valorFixo())
                    .build()).toList();
        }
        return Pagamento.builder()
                .conta(conta)
                .boleto(this.boleto)
                .valorPago(this.valorPago)
                .dataPagamento(LocalDateTime.now())
                .tipoPagamento(this.tipoPagamento)
                .status(StatusPagamento.PENDENTE)
                .taxas(taxasEntidade)
                .build();
    }
}