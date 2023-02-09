package org.tracker.DTO;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "Orderr")
public class Orderr{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Customer customer;

    @OneToMany(targetEntity = OrderItem.class)
    private Set<OrderItem> orderItems;

    public Customer getCustomer(){
        return customer;
    }

    public void setCustomer(Customer customer){
        this.customer = customer;
    }

    public Orderr(){
    }

    public Orderr(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public Orderr(Long id, String name, Customer customer){
        this.id = id;
        this.name = name;
        this.customer = customer;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Set<OrderItem> getOrderItems(){
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems){
        this.orderItems = orderItems;
    }

    @Override
    public String toString(){
        return "Order{" +
                "id=" + id +
                '}';
    }
}
