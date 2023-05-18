package br.ufsm.csi.coffee.controller;

import br.ufsm.csi.coffee.dao.FuncaoDAO;
import br.ufsm.csi.coffee.model.Funcao;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin
@RestController
@RequestMapping("/funcao")
public class FuncaoController {

    @GetMapping()
    public ArrayList<Funcao> getFuncoes(){
        return new FuncaoDAO().getFuncoes();
    }

    @GetMapping("/{id}")
    public Funcao getFuncao(@PathVariable int id){
        return new FuncaoDAO().getFuncao(id);
    }
}
