package org.tracker.Repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.tracker.DTO.OrderItem;
import org.tracker.DTO.Orderr;
import org.tracker.DTO.Product;

public class OrderItemRepository{

    private final EntityManager entityManager;
    private final EntityManagerFactory entityManagerFactory;

    public OrderItemRepository(){
        this.entityManagerFactory = Persistence.createEntityManagerFactory("customer");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public OrderItem find(Long id){
        return entityManager.find(OrderItem.class, id);
    }

    public OrderItem add(OrderItem orderItem){
        entityManager.getTransaction().begin();
        entityManager.persist(orderItem);
        entityManager.getTransaction().commit();

        return orderItem;
    }

    public OrderItem addOrder(long id, Orderr orderr){
        OrderItem orderItem = find(id);
        entityManager.getTransaction().begin();
        orderItem.setOrderr(orderr);
        entityManager.merge(orderItem);
        entityManager.getTransaction().commit();
        return orderItem;
    }

    public OrderItem addProduct(long id, Product product){
        OrderItem orderItem = find(id);
        entityManager.getTransaction().begin();
        orderItem.getProduct().add(product);
        entityManager.merge(orderItem);
        entityManager.getTransaction().commit();
        return orderItem;
    }


    public OrderItem update(OrderItem orderItem){
        OrderItem orderItemToUpdate = find(orderItem.getId());
        entityManager.getTransaction().begin();
        orderItemToUpdate.setCount(orderItem.getCount());
        entityManager.getTransaction().commit();
        return orderItemToUpdate;
    }

    public void delete(OrderItem orderItem){
        entityManager.getTransaction().begin();
        entityManager.remove(orderItem);
        entityManager.getTransaction().commit();
    }

    public void close(){
        this.entityManager.close();
        this.entityManagerFactory.close();
    }
}
