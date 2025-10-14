package com.conta_bancaria.ui_interface.controller;

import com.conta_bancaria.application.dto.ContaAtualizadaDTO;
import com.conta_bancaria.application.dto.ContaResumoDTO;
import com.conta_bancaria.application.dto.SaqueDepositoDTO;
import com.conta_bancaria.application.dto.TransferenciaDTO;
import com.conta_bancaria.application.service.ContaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contas")
@RequiredArgsConstructor
public class ContaController {

    private final ContaService service;

    @GetMapping
    public ResponseEntity<List<ContaResumoDTO>> listarContas() {
        return ResponseEntity.ok(service.exibirContas());
    }

    @GetMapping("/{numero}")
    public ResponseEntity<ContaResumoDTO> buscarContaPorNumero(@PathVariable String numero) {
        return ResponseEntity.ok(service.exibirContasPorNumero(numero));
    }

    @PutMapping("/{numero}")
    public ResponseEntity<ContaResumoDTO> atualizarConta(@PathVariable String numero,
                                                         @Valid @RequestBody ContaAtualizadaDTO dto) {
        return ResponseEntity.ok(service.atualizarConta(numero, dto));
    }

    @DeleteMapping("/{numero}")
    public ResponseEntity<Void> deletarConta(@PathVariable String numero) {
        service.deletarConta(numero);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{numero}/sacar")
    public ResponseEntity<ContaResumoDTO> sacar(@PathVariable String numero,
                                                @Valid @RequestBody SaqueDepositoDTO dto) {
        return ResponseEntity.ok(service.sacar(numero, dto));
    }

    @PostMapping("/{numero}/depositar")
    public ResponseEntity<ContaResumoDTO> depositar(@PathVariable String numero,
                                                    @Valid @RequestBody SaqueDepositoDTO dto) {
        return ResponseEntity.ok(service.depositar(numero, dto));
    }

    @PostMapping("/{numero}/transferir")
    public ResponseEntity<ContaResumoDTO> transferir(@PathVariable String numero,
                                                     @Valid @RequestBody TransferenciaDTO dto) {
        return ResponseEntity.ok(service.transferir(numero, dto));
    }

    @PostMapping("/{numero}/rendimento")
    public ResponseEntity<ContaResumoDTO> rendimento(@PathVariable String numero){
        return ResponseEntity.ok(service.aplicarRendimento(numero));
    }

}
