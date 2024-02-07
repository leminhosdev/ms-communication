package com.leminhosdev.msavaliadorcredito.service;

import com.leminhosdev.msavaliadorcredito.domain.model.*;
import com.leminhosdev.msavaliadorcredito.ex.DadosClienteNotFoundException;
import com.leminhosdev.msavaliadorcredito.ex.ErroComunicacaoMicroservicesException;
import com.leminhosdev.msavaliadorcredito.infra.clients.CartoesResourcerClient;
import com.leminhosdev.msavaliadorcredito.infra.clients.ClientResourceClient;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AvaliadorCreditoService {
    @Autowired
    private CartoesResourcerClient cartoesResourcerClient;
    @Autowired
    private ClientResourceClient clientesClient;
    public SituacaoCliente obterSituacaoCliente(String cpf) throws DadosClienteNotFoundException, ErroComunicacaoMicroservicesException {
      try {
          ResponseEntity<DadosCliente> dadosClienteResponse = clientesClient.dadosClientes(cpf);
          ResponseEntity<List<CartaoCliente>> cartaoResponse = cartoesResourcerClient.getCartoesByClientes(cpf);
          return SituacaoCliente.builder()
                  .cliente(dadosClienteResponse.getBody())
                  .cartoes(cartaoResponse.getBody())
                  .build();
      } catch (FeignException.FeignClientException e){
            int status  =  e.status();
            if(HttpStatus.NOT_FOUND.value() == status){
                throw new DadosClienteNotFoundException();
            }
            throw new ErroComunicacaoMicroservicesException(e.getMessage(), status);
      }
    }

    public RetornoAvaliacaoCliente realizarAvaliacao (String cpf,Long renda)
            throws DadosClienteNotFoundException
            , ErroComunicacaoMicroservicesException{
        try{
            ResponseEntity<DadosCliente> dadosClienteResponse = clientesClient.dadosClientes(cpf);
            ResponseEntity<List<Cartao>> cartoesResponse = cartoesResourcerClient.getCartoesRendaAte(renda);

            List<Cartao> cartoes = cartoesResponse.getBody();
            List<CartaoAprovado> listaCartoesAprovados =  cartoes.stream().map(cartao -> {
                DadosCliente dadosCliente = dadosClienteResponse.getBody();

                BigDecimal limiteBasico = cartao.getLimiteBasico();
                BigDecimal rendaBD= BigDecimal.valueOf(renda);
                BigDecimal idadeBD = BigDecimal.valueOf(dadosCliente.getIdade());
                var fator  = idadeBD.divide(BigDecimal.valueOf(10));
                BigDecimal limiteAprovado = fator.multiply(limiteBasico);

                CartaoAprovado aprovado = new CartaoAprovado();
                aprovado.setBandeira(cartao.getBandeira());
                aprovado.setCartao(cartao.getNome());
                aprovado.setLimiteAprovado(limiteAprovado);

                return aprovado;
            }).collect(Collectors.toList());

            return new RetornoAvaliacaoCliente(listaCartoesAprovados);
        }catch (FeignException.FeignClientException e){
            int status  =  e.status();
            if(HttpStatus.NOT_FOUND.value() == status){
                throw new DadosClienteNotFoundException();
            }
            throw new ErroComunicacaoMicroservicesException(e.getMessage(), status);
        }
    }
}
