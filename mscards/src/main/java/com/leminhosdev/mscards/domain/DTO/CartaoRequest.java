package com.leminhosdev.mscards.domain.DTO;

import com.leminhosdev.mscards.domain.BandeiraCartao;
import com.leminhosdev.mscards.domain.Cartao;

import java.math.BigDecimal;

public record CartaoRequest(String nome, BandeiraCartao bandeira, BigDecimal renda, BigDecimal limiteBasico) {
    public Cartao toModel(){
        return new Cartao(
                this.nome,
                this.bandeira,
                this.renda,
                this.limiteBasico
        );
    }
}
