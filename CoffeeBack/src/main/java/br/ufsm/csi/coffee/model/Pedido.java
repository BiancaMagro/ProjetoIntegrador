package br.ufsm.csi.coffee.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
    private int codigo;
    private int mesa;
    private String descricao;
    private String nome;
    private Status status;
}
