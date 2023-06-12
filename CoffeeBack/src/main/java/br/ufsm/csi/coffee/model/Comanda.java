package br.ufsm.csi.coffee.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comanda {
    private int codigo;
    private String data_criada;
    private String cliente;
    private int mesa;
    private boolean ativo;
}
