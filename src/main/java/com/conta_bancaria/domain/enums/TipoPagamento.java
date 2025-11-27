package com.conta_bancaria.domain.enums;

import com.conta_bancaria.domain.entity.Taxa;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Getter
public enum TipoPagamento {
    AGUA,
    BOLETO,
    LUZ,
    PIX,
    CARTAO
}
