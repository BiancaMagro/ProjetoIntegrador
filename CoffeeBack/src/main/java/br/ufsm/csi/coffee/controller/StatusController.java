package br.ufsm.csi.coffee.controller;

import br.ufsm.csi.coffee.dao.StatusDAO;
import br.ufsm.csi.coffee.model.Status;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/status")
public class StatusController {
    @GetMapping
    public ArrayList<Status> getTodos(){
        return new StatusDAO().getStatuses();
    }

    @GetMapping("/{id}")//pega o que vem no id atraves da variavel da url
    public Status getUm(@PathVariable int id){
        return new StatusDAO().getStatus(id);
    }
}
