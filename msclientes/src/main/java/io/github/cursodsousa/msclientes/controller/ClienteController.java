package io.github.cursodsousa.msclientes.controller;

import io.github.cursodsousa.msclientes.domain.Cliente;
import io.github.cursodsousa.msclientes.dto.ClientSaveRequest;
import io.github.cursodsousa.msclientes.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("clientes")
public class ClienteController {

    private final ClientService clientService;

    public ClienteController(ClientService clientService) {
        this.clientService = clientService;
    }


    @PostMapping
    public ResponseEntity save(@RequestBody ClientSaveRequest clientSaveRequest){
        var cliente = clientSaveRequest.toModel();
        this.clientService.save(cliente);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest().query("cpf={cpf}")
                .buildAndExpand(cliente.getCpf()).toUri();
        return ResponseEntity.created(headerLocation).build();
    }

    @GetMapping
    public ResponseEntity dadosDoCliente(@RequestParam String cpf){
        var cliente = clientService.getByCpf(cpf);
        if(cliente.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/ok")
    public String ok(){
        System.out.println("asad");
        return "ok";

    }
}
