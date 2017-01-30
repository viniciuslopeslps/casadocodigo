package br.com.casadocodigo.shop.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.shop.models.Product;

@Repository
@Transactional
public class ProductDAO {

	@PersistenceContext
	private EntityManager manager;

	public void save(Product product) {
		manager.persist(product);
	}

	public List<Product> list() {
		return manager.createQuery("select p from Product p").getResultList();
	}

	public Product findById(Integer id) {
		return manager.find(Product.class, id);
	}
}
