package com.conta_bancaria.ui_interface;

import com.conta_bancaria.application.dto.ClienteRegistroDTO;
import com.conta_bancaria.application.dto.ClienteResponseDTO;
import com.conta_bancaria.application.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService service;

    @PostMapping
    public ClienteResponseDTO registrarCliente(@RequestBody ClienteRegistroDTO dto) {
        return service.registrarCliente(dto);
    }
}
