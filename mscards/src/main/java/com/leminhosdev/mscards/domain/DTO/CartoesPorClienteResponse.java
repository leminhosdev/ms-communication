package com.leminhosdev.mscards.domain.DTO;

import com.leminhosdev.mscards.domain.ClienteCartao;

import java.math.BigDecimal;

public record CartoesPorClienteResponse(String nome, String bandeira, BigDecimal limiteLiberado) {

    public static CartoesPorClienteResponse fromModel(ClienteCartao model){
        return new CartoesPorClienteResponse(
                model.getCartao().getNome(),
                model.getCartao().getBandeira().toString(),
                model.getLimite()
        );
    }
}
