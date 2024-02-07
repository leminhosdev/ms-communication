package com.leminhosdev.msavaliadorcredito.domain.model;

import lombok.Data;

import java.util.List;
@Data
public class RetornoAvaliacaoCliente {
    private List<CartaoAprovado> cartoes;

    public RetornoAvaliacaoCliente(List<CartaoAprovado> cartoes) {
        this.cartoes = cartoes;
    }
}
