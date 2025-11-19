package com.conta_bancaria.application.dto.conta_e_transferencias;

import com.conta_bancaria.domain.entity.Taxa;

import java.math.BigDecimal;

public class TaxaDTO{
        public record TaxaRequestDTO(
                String descricao,
                BigDecimal percentual
        ){
            public static TaxaRequestDTO fromEntity(Taxa taxa){
                return new TaxaRequestDTO(
                        taxa.getDescricao(),
                        taxa.getPercentual()
                );
            }
        }
        public record TaxaResponseDTO(
                String descricao,
                BigDecimal percentual,
                BigDecimal valorFixo
        ) {
            public static TaxaResponseDTO fromEntity(Taxa taxa){
                return new TaxaResponseDTO(
                        taxa.getDescricao(),
                        taxa.getPercentual(),
                        taxa.getValorFixo()
                );
            }
        }
}
