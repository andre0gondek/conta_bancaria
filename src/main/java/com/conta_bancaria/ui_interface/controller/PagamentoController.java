package com.conta_bancaria.ui_interface.controller;

import com.conta_bancaria.application.dto.ClienteRegistroDTO;
import com.conta_bancaria.application.dto.ClienteResponseDTO;
import com.conta_bancaria.application.dto.PagamentoRequestDTO;
import com.conta_bancaria.application.dto.PagamentoResponseDTO;
import com.conta_bancaria.application.service.PagamentoAppService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/pagarBoleto")
@RequiredArgsConstructor
public class PagamentoController {
    private final PagamentoAppService service;

    @PostMapping
    public ResponseEntity<PagamentoResponseDTO> gerarPagamento(@Valid @RequestBody PagamentoRequestDTO dto, boolean respostaValidaMQTT) {
        PagamentoResponseDTO createdPagamento = service.processarPagamento(dto, respostaValidaMQTT);

        return ResponseEntity.created(URI.create("/api/pagarBoleto" +
                createdPagamento.boleto())).body(createdPagamento);
    }
}
