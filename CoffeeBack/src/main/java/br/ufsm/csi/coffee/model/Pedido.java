package br.ufsm.csi.coffee.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
    private int codigo;
    private String dataPedido;
    private int quantidade;
    private int comanda;
    private Produto produto;
    private Status status;
}
