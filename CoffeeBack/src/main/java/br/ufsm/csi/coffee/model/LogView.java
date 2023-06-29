package br.ufsm.csi.coffee.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogView {
    private String cliente;
    private String produto;
    private String data;
    private int quantidade;
    private double valor;
}
