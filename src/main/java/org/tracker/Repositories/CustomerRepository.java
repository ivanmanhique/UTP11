package org.tracker.Repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.tracker.DTO.Customer;
import org.tracker.DTO.Orderr;

public class CustomerRepository{
    private final EntityManager entityManager;
    private final EntityManagerFactory entityManagerFactory;

    public CustomerRepository(){
        this.entityManagerFactory = Persistence.createEntityManagerFactory("customer");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public Customer find(Long id){
        return entityManager.find(Customer.class, id);
    }

    public Customer add(Customer customer){
        entityManager.getTransaction().begin();
        entityManager.persist(customer);
        entityManager.getTransaction().commit();

        return customer;
    }

    public Customer update(Customer customer){
        Customer customerToUpdate = find(customer.getId());

        entityManager.getTransaction().begin();

        customerToUpdate.setFirstName(customer.getFirstName());
        customerToUpdate.setLastName(customer.getLastName());

        entityManager.getTransaction().commit();

        return customerToUpdate;
    }

    public void delete(Customer customer){
        entityManager.getTransaction().begin();
        entityManager.remove(customer);
        entityManager.getTransaction().commit();
    }

    public void addOrder(Long id, Orderr order){
        entityManager.getTransaction().begin();
        Customer customer = find(id);
        if (customer != null){
            customer.getOrders().add(order);
        }
        entityManager.persist(customer);
        entityManager.getTransaction().commit();
    }

    public void removeOrder(Long id, Orderr order){
        entityManager.getTransaction().begin();
        Customer customer = find(id);
        if (customer != null){
            customer.getOrders().remove(order);
        }
        entityManager.persist(customer);
        entityManager.getTransaction().commit();
    }


    public void close(){
        this.entityManager.close();
        this.entityManagerFactory.close();
    }


}
