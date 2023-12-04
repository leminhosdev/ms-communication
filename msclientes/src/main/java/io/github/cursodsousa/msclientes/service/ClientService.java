package io.github.cursodsousa.msclientes.service;

import io.github.cursodsousa.msclientes.domain.Cliente;
import io.github.cursodsousa.msclientes.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClientService {
    private final ClienteRepository repository;

    public ClientService(ClienteRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Cliente save(Cliente cliente){
        return repository.save(cliente);
    }



    public Optional<Cliente> getByCpf(String cpf){
        return repository.findByCpf(cpf);
    }
}
