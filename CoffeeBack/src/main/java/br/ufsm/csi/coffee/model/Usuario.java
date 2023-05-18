package br.ufsm.csi.coffee.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    private String login;
    private String senha;
    private String token;
    private Funcao funcao;
}
