package com.leminhosdev.mscards.controller;

import com.leminhosdev.mscards.domain.Cartao;
import com.leminhosdev.mscards.domain.ClienteCartao;
import com.leminhosdev.mscards.domain.DTO.CartaoRequest;
import com.leminhosdev.mscards.domain.DTO.CartoesPorClienteResponse;
import com.leminhosdev.mscards.service.CartaoService;
import com.leminhosdev.mscards.service.ClienteCartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cartoes")
public class CartoesResource {
    @Autowired
    private CartaoService cartaoService;

    @Autowired
    private ClienteCartaoService clienteCartaoService;

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody CartaoRequest cartaoRequest){
        cartaoService.save(cartaoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = "renda")
    public ResponseEntity<List<Cartao>> getCartoesRendaate(@RequestParam("renda") Long renda){
        List<Cartao> list = cartaoService.getCartoesRendaMenorIgual(renda);
        return ResponseEntity.ok(list);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CartoesPorClienteResponse>> getCartoesByClientes(
            @RequestParam("cpf") String cpf){
        List<ClienteCartao> lista = clienteCartaoService.listCartoesByCpf(cpf);
        List<CartoesPorClienteResponse> resultList = lista
                .stream()
                .map(CartoesPorClienteResponse::fromModel)
                .collect(Collectors.toList());

        return ResponseEntity.ok(resultList);
    }


 }
