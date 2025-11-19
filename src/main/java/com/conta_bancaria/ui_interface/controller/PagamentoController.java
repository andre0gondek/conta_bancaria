package com.conta_bancaria.ui_interface.controller;

import com.conta_bancaria.application.dto.conta_e_transferencias.PagamentoRequestDTO;
import com.conta_bancaria.application.dto.conta_e_transferencias.PagamentoResponseDTO;
import com.conta_bancaria.application.service.PagamentoAppService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/pagarBoleto")
@RequiredArgsConstructor
public class PagamentoController {
    private final PagamentoAppService service;

    @PostMapping
    public ResponseEntity<PagamentoResponseDTO> processarPagamento(
            @Valid @RequestBody PagamentoRequestDTO dto,
            @RequestParam boolean respostaValidaMQTT) {
        PagamentoResponseDTO createdPagamento = service.processarPagamento(dto, respostaValidaMQTT);

        return ResponseEntity.created(URI.create("/api/pagarBoleto/" +
                createdPagamento.boleto())).body(createdPagamento);
    }
}
