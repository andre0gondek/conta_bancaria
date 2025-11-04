package com.conta_bancaria.application.dto;

import com.conta_bancaria.domain.entity.Cliente;
import com.conta_bancaria.domain.entity.Conta;
import com.conta_bancaria.domain.entity.Pagamento;
import com.conta_bancaria.domain.entity.Taxa;
import com.conta_bancaria.domain.enums.StatusPagamento;
import java.math.BigDecimal;
import java.util.List;

public record PagamentoResponseDTO(
        String contaNumero,
        String boleto,
        BigDecimal valorPago,
        String dataPagamento,
        StatusPagamento status,
        List<TaxaDTO.TaxaResponseDTO> taxas
) {
    public static PagamentoResponseDTO fromEntity(Pagamento pagamento) {
        List<TaxaDTO.TaxaResponseDTO> taxas = pagamento.getTaxas().stream().map(TaxaDTO.TaxaResponseDTO::fromEntity).toList();

        return new PagamentoResponseDTO(
                pagamento.getId(),
                pagamento.getBoleto(),
                pagamento.getValorPago(),
                pagamento.getDataPagamento(),
                pagamento.getStatus(),
                taxas
        );
    }
}
