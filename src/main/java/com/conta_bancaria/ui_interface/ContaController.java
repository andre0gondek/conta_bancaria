package com.conta_bancaria.ui_interface;

import com.conta_bancaria.application.dto.ContaResumoDTO;
import com.conta_bancaria.application.service.ContaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/contas")
@RequiredArgsConstructor
public class ContaController {

    private final ContaService service;

    @GetMapping
    public ResponseEntity<List<ContaResumoDTO>> listarContas(){
        return ResponseEntity.ok(service.exibirContas());
    }

    @GetMapping
    public  ResponseEntity<ContaResumoDTO> buscarContaPorNumero(String num){
        return ResponseEntity.ok(service.exibirContasPorNumero(num));
    }

}
