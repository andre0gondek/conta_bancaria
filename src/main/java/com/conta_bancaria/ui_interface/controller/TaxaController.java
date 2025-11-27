package com.conta_bancaria.ui_interface.controller;

import com.conta_bancaria.application.dto.conta_e_transferencias.TaxaDTO;
import com.conta_bancaria.application.service.TaxaService;
import com.conta_bancaria.domain.enums.TipoPagamento;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/taxas")
@RequiredArgsConstructor
public class TaxaController {
    private final TaxaService service;

    @PostMapping
    public ResponseEntity<TaxaDTO.TaxaResponseDTO> criarTaxa(
            @RequestBody TaxaDTO.TaxaRequestDTO dto,
            @RequestParam TipoPagamento tipo) {
        return ResponseEntity.ok(service.criarTaxa(dto, tipo));
    }

    @GetMapping
    public ResponseEntity<List<TaxaDTO.TaxaResponseDTO>> listar() {
        return ResponseEntity.ok(service.listarTaxas());
    }
}