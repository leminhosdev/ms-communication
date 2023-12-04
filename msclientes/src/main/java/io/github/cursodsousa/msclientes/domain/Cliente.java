package io.github.cursodsousa.msclientes.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String cpf;
    @Column
    private String nome;
    @Column
    private Integer idade;

    public Cliente() {
    }

    public Cliente(Long id, String cpf, String nome, Integer idade) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.idade = idade;
    }

    public Cliente(String cpf, String nome, Integer idade) {
        this.cpf = cpf;
        this.nome = nome;
        this.idade = idade;
    }

}
