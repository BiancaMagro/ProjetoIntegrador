package br.ufsm.csi.coffee.security;

import br.ufsm.csi.coffee.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtil {

    public static final long TEMPO_VIDA = Duration.ofMinutes(30).toMillis();

    public String geraToken(Usuario usuario){

        final Map<String, Object> claims = new HashMap<>();
        claims.put("sub", usuario.getLogin());
        claims.put("funcao", usuario.getFuncao().getFuncao());

        return Jwts.builder().setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis()+JWTUtil.TEMPO_VIDA))
                .signWith(SignatureAlgorithm.HS256, "bebel")
                .compact();
    }

    public String getUsernameToken(String token){
        if (token != null){
            return this.parseToken(token).getSubject();
        }else {
            return null;
        }
    }

    public boolean isTokenExpired(String token){
        return this.parseToken(token).getExpiration().before(new Date());
    }

    private Claims parseToken(String token){
        return Jwts.parser().setSigningKey("bebel")
                .parseClaimsJws(token.replace("Bearer", ""))
                .getBody();

    }
}
