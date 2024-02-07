package com.leminhosdev.mscards.service;

import com.leminhosdev.mscards.domain.ClienteCartao;
import com.leminhosdev.mscards.repository.ClienteCartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ClienteCartaoService {

    @Autowired
    private ClienteCartaoRepository clienteCartaoRepository;

    public List<ClienteCartao> listCartoesByCpf(String cpf){
        return clienteCartaoRepository.findByCpf(cpf);
    }
}
