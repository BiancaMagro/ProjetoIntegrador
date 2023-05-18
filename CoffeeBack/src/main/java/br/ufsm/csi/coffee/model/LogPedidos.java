package br.ufsm.csi.coffee.model;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogPedidos {
    private Pedido pedido;
    private ArrayList<Produto> produto;
}
