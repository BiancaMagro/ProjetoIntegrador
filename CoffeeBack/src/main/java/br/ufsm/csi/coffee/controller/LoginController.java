package br.ufsm.csi.coffee.controller;

import br.ufsm.csi.coffee.model.Funcao;
import br.ufsm.csi.coffee.model.Usuario;
import br.ufsm.csi.coffee.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @CrossOrigin
    @PostMapping()
    public ResponseEntity<Object> logar(@RequestBody Usuario usuario){
        System.out.println("teste");

        try {

            final Authentication authentication = this.authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(usuario.getLogin(), usuario.getSenha()));

            if (authentication.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(authentication);

                Funcao funcao = new Funcao();

                funcao.setFuncao(authentication.getAuthorities().toString().replace("[", "").replace("]", ""));
                usuario.setFuncao(funcao);

                String token = new JWTUtil().geraToken(usuario);

                usuario.setToken(token);
                usuario.setSenha("");

                return new ResponseEntity<>(usuario, HttpStatus.OK);
            }

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Usuario ou senha incorretos!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Usuario ou senha incorretos!", HttpStatus.BAD_REQUEST);
    }
}
