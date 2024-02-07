package com.leminhosdev.msavaliadorcredito.ex;

import com.leminhosdev.msavaliadorcredito.domain.model.SituacaoCliente;

public class DadosClienteNotFoundException extends Exception{

    public DadosClienteNotFoundException(){
        super("Dados do cliente não encontrados para o CPF informado");
    }
}
