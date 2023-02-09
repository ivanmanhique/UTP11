package org.tracker.Repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.tracker.DTO.Customer;
import org.tracker.DTO.Orderr;
import org.tracker.DTO.Product;

public class OrderRepository{
    private final EntityManager entityManager;
    private final EntityManagerFactory entityManagerFactory;

    public OrderRepository(){
        this.entityManagerFactory = Persistence.createEntityManagerFactory("customer");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public Orderr find(Long id){
        return entityManager.find(Orderr.class, id);
    }

    public Orderr add(Orderr order){
        entityManager.getTransaction().begin();
        entityManager.persist(order);
        entityManager.getTransaction().commit();

        return order;
    }

    public void addCustomer(Long id, Customer customer){
        entityManager.getTransaction().begin();
        Orderr orderr = find(id);
        if (orderr != null){
            orderr.setCustomer(customer);
        }
        entityManager.merge(orderr);
        entityManager.getTransaction().commit();
    }

    public Orderr update(Orderr order){
        Orderr orderToUpdate = find(order.getId());
        entityManager.getTransaction().begin();
        orderToUpdate.setName(order.getName());
        entityManager.getTransaction().commit();
        return orderToUpdate;
    }

    public void delete(Orderr order){
        entityManager.getTransaction().begin();
        entityManager.remove(order);
        entityManager.getTransaction().commit();
    }

    public void close(){
        this.entityManager.close();
        this.entityManagerFactory.close();
    }
}
