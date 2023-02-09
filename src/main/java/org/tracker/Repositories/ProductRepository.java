package org.tracker.Repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.tracker.DTO.Product;

public class ProductRepository{

    private final EntityManager entityManager;
    private final EntityManagerFactory entityManagerFactory;

    public ProductRepository(){
        this.entityManagerFactory = Persistence.createEntityManagerFactory("customer");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public Product find(Long id){
        return entityManager.find(Product.class, id);
    }

    public Product add(Product product){
        entityManager.getTransaction().begin();
        entityManager.persist(product);
        entityManager.getTransaction().commit();

        return product;
    }


    public Product update(Product product){
        Product productToUpdate = find(product.getId());

        entityManager.getTransaction().begin();

        productToUpdate.setName(product.getName());
        productToUpdate.setPrice(product.getPrice());

        entityManager.getTransaction().commit();

        return productToUpdate;
    }

    public void delete(Product product){
        entityManager.getTransaction().begin();
        entityManager.remove(product);
        entityManager.getTransaction().commit();
    }

    public void close(){
        this.entityManager.close();
        this.entityManagerFactory.close();
    }

}
