package br.ufsm.csi.coffee.controller;

import br.ufsm.csi.coffee.dao.PedidoDAO;
import br.ufsm.csi.coffee.model.Comanda;
import br.ufsm.csi.coffee.model.Pedido;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    @GetMapping
    public ArrayList<Pedido> getPedidos(){
        return new PedidoDAO().getPedidos();
    }

    @GetMapping("/cozinha")
    public ArrayList<Pedido> getCozinha(){
        return new PedidoDAO().getCozinha();
    }

    @GetMapping("/{id}")
    public Pedido getPedido(@PathVariable int id){
        return new PedidoDAO().getPedido(id);
    }

    @PostMapping
    public void criar(@RequestBody Pedido pedido){
        System.out.println(pedido.getDataPedido());
        new PedidoDAO().setPedido(pedido);
    }

    @PutMapping("/{id}")
    public void editar(@RequestBody Pedido pedido, @PathVariable int id){
        new PedidoDAO().editar(pedido, id);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable int id){
        new PedidoDAO().excluir(id);
    }
    
    @GetMapping("/comandas")
    public ArrayList<Comanda> getComandas(){
        return new PedidoDAO().getComandas();
    }

    @PostMapping("/comandas")
    public void criarComanda(@RequestBody Comanda comanda){
        new PedidoDAO().addComanda(comanda);
    }

    @GetMapping("/comanda/{id}")
    public ArrayList<Pedido> getComanda(@PathVariable int id){
        return new PedidoDAO().getPedidos(id);
    }
}
