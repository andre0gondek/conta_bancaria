package com.conta_bancaria.ui_interface;

import com.conta_bancaria.application.dto.ClienteRegistroDTO;
import com.conta_bancaria.application.dto.ClienteResponseDTO;
import com.conta_bancaria.application.service.ClienteService;
import com.conta_bancaria.domain.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<List<ClienteResponseDTO>> exibirCliente(){
        return ResponseEntity.ok(service.exibirClientesAtivos());
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<ClienteResponseDTO> buscarClientePorCpf(@PathVariable String cpf){
        return ResponseEntity.ok(service.buscarClientePorCpf(cpf));
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<ClienteResponseDTO> atualizarCliente(@PathVariable String cpf, @RequestBody ClienteRegistroDTO dto) {
        service.atualizarCliente(cpf, dto);
        return ResponseEntity.ok().build(); // Retornar o cliente atualizado
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deletarCliente(@PathVariable String cpf) {
        service.deletarCliente(cpf);
        return ResponseEntity.noContent().build(); // Retornar 204 No Content
    }
}
