package br.ufsm.csi.coffee.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufsm.csi.coffee.dao.ProdutoDAO;
import br.ufsm.csi.coffee.model.Produto;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("produtos")
public class ProdutoController {
    @GetMapping()
    public ArrayList<Produto> getProdutos(){
        System.out.println(new ProdutoDAO().getProdutos());
        return new ProdutoDAO().getProdutos();
    }
    @GetMapping("/all")
    public ArrayList<Produto> getAllProdutos(){
        return new ProdutoDAO().getAllProdutos();
    }
    @GetMapping("/byId/{id}")
    public Produto getProduto(@PathVariable int id){
        return new ProdutoDAO().getById(id);
    }
    @PostMapping
    public void addProduto(@RequestBody Produto produto){
        System.out.println(produto.toString());
        new ProdutoDAO().addProduto(produto);
    }
    @PutMapping("/{id}")
    public void editProduto(@RequestBody Produto produto, @PathVariable int id){
        new ProdutoDAO().editaProduto(produto, id);
    }
    @DeleteMapping("/{id}")
    public void deleteProduto(@PathVariable int id){
        new ProdutoDAO().deleteProduto(id);
    }

}
