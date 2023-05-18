package br.ufsm.csi.coffee.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureAutenticacao(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(this.userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public FiltroAutenticacao filtroAutenticacao() throws Exception{
        return new FiltroAutenticacao();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeHttpRequests()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers(HttpMethod.GET, "/pedidos").hasAnyAuthority("GARCOM")
                .antMatchers(HttpMethod.GET, "/pedidos/cozinha").hasAnyAuthority("COZINHA")
                .antMatchers(HttpMethod.GET, "/pedidos/{id}").hasAnyAuthority("GARCOM", "COZINHA")
                .antMatchers(HttpMethod.POST, "/pedidos").hasAnyAuthority("GARCOM")
                .antMatchers(HttpMethod.PUT, "/pedidos/{id}").hasAnyAuthority("GARCOM", "COZINHA")
                .antMatchers(HttpMethod.DELETE, "/pedidos/{id}").hasAnyAuthority("GARCOM")
                .antMatchers(HttpMethod.GET, "/status").hasAnyAuthority("GARCOM", "COZINHA")
                .antMatchers(HttpMethod.GET, "/status/{id}").hasAnyAuthority("GARCOM", "COZINHA")
                .antMatchers(HttpMethod.GET, "/funcao").hasAnyAuthority("GARCOM", "ADMIN")
                .antMatchers(HttpMethod.GET, "/funcao/{id}").hasAnyAuthority("GARCOM")
                .antMatchers(HttpMethod.GET, "/usuarios").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/usuarios/{login}").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/usuarios").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT, "/usuarios/{login}").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/usuarios/{login}").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/usuarios/prontos").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/produtos").hasAnyAuthority("ADMIN", "GARCOM", "COZINHA")
                .antMatchers(HttpMethod.GET, "/produtos/{id}").hasAnyAuthority("ADMIN", "GARCOM", "COZINHA")
                .antMatchers(HttpMethod.GET, "/produtos/all").hasAnyAuthority("ADMIN", "GARCOM", "COZINHA")
                .antMatchers(HttpMethod.POST, "/produtos").hasAnyAuthority("ADMIN", "GARCOM", "COZINHA")
                .antMatchers(HttpMethod.PUT, "/produtos/{id}").hasAnyAuthority("ADMIN", "GARCOM", "COZINHA")
                .antMatchers(HttpMethod.DELETE, "/produtos/{id}").hasAnyAuthority("ADMIN", "GARCOM", "COZINHA");
        http.addFilterBefore(this.filtroAutenticacao(), UsernamePasswordAuthenticationFilter.class);
    }
    
    @Bean
    public CorsFilter corsFilter() {
        final var config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        final var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}   
