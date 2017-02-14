package br.com.casadocodigo.shop.daos;

import br.com.casadocodigo.shop.models.Product;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class ProductDAO {

    @PersistenceContext
    private EntityManager manager;

    public void save(Product product) {
        manager.persist(product);
    }

    //join fetch ele carrega o "precos" no momento do select
    public List<Product> list() {
        return manager.createQuery("select distinct (p) from Product p join fetch p.prices").getResultList();
    }

    public Product find(Integer id) {
        return manager.createQuery("select distinct(p) from Product p join fetch p.prices price where p.id = :id", Product.class)
                .setParameter("id", id).getSingleResult();
    }
}
