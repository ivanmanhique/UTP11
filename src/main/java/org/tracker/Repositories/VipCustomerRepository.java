package org.tracker.Repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import org.tracker.DTO.VipCustomer;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public class VipCustomerRepository{

    private final EntityManager entityManager;
    private final EntityManagerFactory entityManagerFactory;

    public VipCustomerRepository(){
        this.entityManagerFactory = Persistence.createEntityManagerFactory("customer");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public VipCustomer find(Long id){
        return entityManager.find(VipCustomer.class, id);
    }

    public VipCustomer add(VipCustomer vipCustomer){
        entityManager.getTransaction().begin();
        entityManager.persist(vipCustomer);
        entityManager.getTransaction().commit();

        return vipCustomer;
    }

    public VipCustomer update(VipCustomer vipCustomer){
        VipCustomer vipCustomerToUpdate = find(vipCustomer.getId());

        entityManager.getTransaction().begin();

        vipCustomerToUpdate.setDiscountRate(vipCustomer.getDiscountRate());

        entityManager.getTransaction().commit();

        return vipCustomerToUpdate;
    }

    public void delete(VipCustomer vipCustomer){
        entityManager.getTransaction().begin();
        entityManager.remove(vipCustomer);
        entityManager.getTransaction().commit();
    }

    public void creatingVipCustomerW5Percent(){
        Query q = entityManager.createNativeQuery("SELECT c.id\n" +
                "FROM customer c\n" +
                "JOIN Orderr o on c.id = o.customer_id\n" +
                "JOIN orderitem i on o.id = i.orderr_id\n" +
                "JOIN orderitem_product x on i.id = x.orderitem_id\n" +
                "Join Product p on x.product_id = p.id\n" +
                "WHERE p.price*i.count >1000 &&  p.price*i.count<5000;");
        List<Long> ids =  q.getResultList();

        for (Long a : ids){
            if(a>1l){
                VipCustomer vipCustomer1 = new VipCustomer();
                vipCustomer1.setId(a);
                vipCustomer1.setDiscountRate(0.05);
                add(vipCustomer1);
            }
        }

    }

    public void creatingVipCustomerW10Percent(){
        Query q = entityManager.createNativeQuery("SELECT c.id\n" +
                "FROM customer c\n" +
                "JOIN Orderr o on c.id = o.customer_id\n" +
                "JOIN orderitem i on o.id = i.orderr_id\n" +
                "JOIN orderitem_product x on i.id = x.orderitem_id\n" +
                "Join Product p on x.product_id = p.id\n" +
                "WHERE p.price*i.count >5000;");
        List<Long> ids =  q.getResultList();

        for (Long a : ids){
            if(a>1l){
                VipCustomer vipCustomer1 = new VipCustomer();
                vipCustomer1.setId(a);
                vipCustomer1.setDiscountRate(0.1);
                add(vipCustomer1);
            }
        }

    }

    public void close(){
        this.entityManager.close();
        this.entityManagerFactory.close();
    }
}
