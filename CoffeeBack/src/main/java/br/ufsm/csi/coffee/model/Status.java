package br.ufsm.csi.coffee.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Status {
    private int codigo;
    private String status;
}
