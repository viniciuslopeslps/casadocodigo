package br.com.casadocodigo.shop.conf;

import br.com.casadocodigo.shop.daos.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Classe de configuração do Spring security
 */
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDAO userDAO;


    //CONFIGURA AS PERMISSOES DE ACORDO COM AS ROLES DOS USUARIOS APARTIR DA URL
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/produtos/form").hasRole("ADMIN")
                .antMatchers("/carrinho/**").permitAll()
                .antMatchers("/pagamento/**").permitAll()
                .antMatchers(HttpMethod.POST, "/produtos").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/produtos").permitAll()
                .antMatchers("/produtos/**").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/resources/**").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login").permitAll()
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
    }

    // configuração para autenticação de usuários com spring security
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDAO)
                .passwordEncoder(new BCryptPasswordEncoder()); //criptografa a senha
    }
}