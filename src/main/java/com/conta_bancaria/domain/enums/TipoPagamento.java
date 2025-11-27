package com.conta_bancaria.domain.enums;

import com.conta_bancaria.domain.entity.Taxa;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Getter
public enum TipoPagamento {
    AGUA(Arrays.asList(
            Taxa.builder()
                    .descricao("Tarifa SABESP (10.0%)")
                    .percentual(new BigDecimal("0.1"))
                    .valorFixo(BigDecimal.ZERO)
                    .build()
    )),

    BOLETO(Arrays.asList(
            Taxa.builder()
                    .descricao("Tarifa de Processamento (Fixo)")
                    .percentual(BigDecimal.ZERO)
                    .valorFixo(new BigDecimal("5.00"))
                    .build()
    )),

    LUZ(Arrays.asList(
            Taxa.builder()
                    .descricao("Tarifa enel (5.0%)")
                    .percentual(new BigDecimal("0.05"))
                    .valorFixo(BigDecimal.ZERO)
                    .build()
    ));

    private final List<Taxa> taxas;

    TipoPagamento(List<Taxa> taxas) {
        this.taxas = taxas;
    }

}
