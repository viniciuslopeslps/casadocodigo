package br.com.casadocodigo.shop.conf;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Classe de configuração do Spring security
 */
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    //CONFIGURA AS PERMISSOES DE ACORDO COM AS ROLES DOS USUARIOS APARTIR DA URL
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/produtos/form").hasRole("ADMIN")
                .antMatchers("/carrinho").permitAll()
                .antMatchers(HttpMethod.POST, "/produtos").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/produtos").permitAll()
                .antMatchers("/produtos/**").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/resources/**").permitAll()
                .anyRequest().authenticated().and().formLogin();
    }
}
