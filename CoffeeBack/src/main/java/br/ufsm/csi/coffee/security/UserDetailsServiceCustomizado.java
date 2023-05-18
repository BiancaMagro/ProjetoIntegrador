package br.ufsm.csi.coffee.security;

import br.ufsm.csi.coffee.dao.UsuarioDAO;
import br.ufsm.csi.coffee.model.Usuario;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceCustomizado implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = new UsuarioDAO().getUser(username);
        if (usuario == null){
            throw new UsernameNotFoundException("Usuario ou senha invalidos!");
        }else{
            UserDetails user = User.withUsername(usuario.getLogin()).password(usuario.getSenha()).authorities(usuario.getFuncao().getFuncao()).build();
            return user;
        }
    }
}
