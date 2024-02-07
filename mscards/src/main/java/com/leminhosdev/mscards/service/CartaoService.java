package com.leminhosdev.mscards.service;

import com.leminhosdev.mscards.domain.Cartao;
import com.leminhosdev.mscards.domain.DTO.CartaoRequest;
import com.leminhosdev.mscards.repository.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartaoService {
    @Autowired
    private CartaoRepository cartaoRepository;

    public Cartao save(CartaoRequest cartaoDTO){
        Cartao cartao = cartaoDTO.toModel();
        return cartaoRepository.save(cartao);
    }

    public List<Cartao> getCartoesRendaMenorIgual(Long renda){
        var rendaBigdecimal = BigDecimal.valueOf(renda);
        return cartaoRepository.findByRendaLessThanEqual(rendaBigdecimal);
    }
}
