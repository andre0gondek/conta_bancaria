package com.conta_bancaria.ui_interface;

import com.conta_bancaria.application.dto.ClienteDTO;
import com.conta_bancaria.application.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class ClienteController {
  /*  @Autowired
    ClienteService clienteService;

    @GetMapping
    public List<ClienteDTO> listarClientes(){
        return clienteService.listarClientes();
    }

    @GetMapping("/{id}")
    public ClienteDTO listarClientesPorId(@PathVariable String id){
        return clienteService.buscarClientesPorId(id);
    }

    @PostMapping
    public ClienteDTO salvarCliente(@RequestBody ClienteDTO clienteDTO){
        return clienteService.salvarCliente(clienteDTO);
    }

    @PutMapping("/{id}")
    public ClienteDTO atualizarCliente(@RequestBody String id, @RequestBody ClienteDTO clienteDTO){
        return clienteService.atualizarCliente(id, clienteDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCliente(@PathVariable String id){
        clienteService.deletarCliente(id);
    }*/
}
