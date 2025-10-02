package com.conta_bancaria.ui_interface;

import com.conta_bancaria.application.dto.ContaAtualizadaDTO;
import com.conta_bancaria.application.dto.ContaResumoDTO;
import com.conta_bancaria.application.dto.SaqueDepositoDTO;
import com.conta_bancaria.application.dto.TransferenciaDTO;
import com.conta_bancaria.application.service.ContaService;
import com.conta_bancaria.domain.entity.Conta;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.internal.engine.groups.ValidationOrder;
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
    public ResponseEntity<ContaResumoDTO> buscarContaPorNumero(@PathVariable String num) {
        return ResponseEntity.ok(service.exibirContasPorNumero(num));
    }

    @PutMapping("/{numero}")
    public ResponseEntity<ContaResumoDTO> atualizarConta(@PathVariable String numero, @RequestBody ContaAtualizadaDTO dto) {
        return ResponseEntity.ok(service.atualizarConta(numero, dto));
    }

    @DeleteMapping("/{numero}")
    public ResponseEntity<Void> deletarConta(@PathVariable String numero) {
        service.deletarConta(numero);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{numero}/sacar")
    public ResponseEntity<ContaResumoDTO> sacar(@PathVariable String numero, @RequestBody SaqueDepositoDTO dto) {
        return ResponseEntity.ok(service.sacar(numero, dto));
    }

    @PostMapping("/{numeroDaConta}/depositar")
    public ResponseEntity<ContaResumoDTO> depositar(@PathVariable String numero,
                                                    @RequestBody SaqueDepositoDTO dto) {
        return ResponseEntity.ok(service.depositar(numero, dto));
    }

    @PostMapping("/{numero}/transferir")
    public ResponseEntity<ContaResumoDTO> transferir(@PathVariable String numero, @RequestBody TransferenciaDTO dto) {
        return ResponseEntity.ok(service.transferir(numero, dto));
    }

}
