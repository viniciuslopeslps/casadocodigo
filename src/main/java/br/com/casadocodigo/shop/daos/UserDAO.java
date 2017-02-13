package br.com.casadocodigo.shop.daos;

import br.com.casadocodigo.shop.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
// o DAO do usuáiro precisa implementar o userDetailService porque o spring security usa isso.
public class UserDAO implements UserDetailsService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User loadUserByUsername(String email) {
        List<User> users = entityManager.createQuery("select u from User u where u.email = :email",
                User.class)
                .setParameter("email", email)
                .getResultList();

        if (users.isEmpty()) {
            throw new UsernameNotFoundException("Usuário inexistente");
        }
        return users.get(0);
    }
}
