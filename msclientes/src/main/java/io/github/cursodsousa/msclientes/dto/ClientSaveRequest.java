package io.github.cursodsousa.msclientes.dto;

import io.github.cursodsousa.msclientes.domain.Cliente;

public record ClientSaveRequest(String cpf, String nome, Integer idade) {

    public Cliente toModel(){
        return new Cliente(cpf, nome, idade);
    }
}
