package br.ufsm.csi.coffee.controller;

import br.ufsm.csi.coffee.dao.PedidoDAO;
import br.ufsm.csi.coffee.dao.UsuarioDAO;
import br.ufsm.csi.coffee.model.Pedido;
import br.ufsm.csi.coffee.model.Usuario;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @GetMapping
    public ArrayList<Usuario> getUsuarios(){
        return new UsuarioDAO().getUsers();
    }
    @GetMapping("/{login}")
    public Usuario getUser(@PathVariable String login){
        return new UsuarioDAO().getUsuario(login);
    }

    @PostMapping
    public void setUser(@RequestBody Usuario usuario){
        new UsuarioDAO().setUser(usuario);
    }

    @PutMapping("/{login}")
    public void updUser(@RequestBody Usuario usuario, @PathVariable String login){
        new UsuarioDAO().editar(usuario, login);
    }

    @DeleteMapping("/{login}")
    public void deleteUser(@PathVariable String login){
        new UsuarioDAO().excluir(login);
    }

    
}
