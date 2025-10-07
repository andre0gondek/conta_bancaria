package com.conta_bancaria.ui_interface.controller;

import com.conta_bancaria.application.dto.ClienteAtualizadoDTO;
import com.conta_bancaria.application.dto.ClienteRegistroDTO;
import com.conta_bancaria.application.dto.ClienteResponseDTO;
import com.conta_bancaria.application.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/cliente")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService service;

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> registrarCliente(@RequestBody ClienteRegistroDTO dto) {
        ClienteResponseDTO createdCliente = service.registrarCliente(dto);

        return ResponseEntity.created(URI.create("/api/cliente/cpf/" +
                createdCliente.cpf())).body(createdCliente);
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> exibirCliente() {
        return ResponseEntity.ok(service.exibirClientesAtivos());
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<ClienteResponseDTO> buscarClientePorCpf(@PathVariable String cpf) {
        return ResponseEntity.ok(service.buscarClientePorCpf(cpf));
    }

    @PutMapping("/cpf/{cpf}")
    public ResponseEntity<ClienteResponseDTO> atualizarCliente(@PathVariable String cpf, @RequestBody ClienteAtualizadoDTO dto) {
        ClienteResponseDTO clienteAtualizado = service.atualizarCliente(cpf, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/cpf/{cpf}")
    public ResponseEntity<Void> deletarCliente(@PathVariable String cpf) {
        service.deletarCliente(cpf);
        return ResponseEntity.ok().build();
    }
}
