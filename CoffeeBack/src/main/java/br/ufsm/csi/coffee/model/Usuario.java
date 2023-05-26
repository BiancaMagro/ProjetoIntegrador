package br.ufsm.csi.coffee.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    private String nome;
    private String cpf;
    private String login;
    private String senha;
    private String token;
    private Funcao funcao;
}
