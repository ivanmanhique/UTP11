package org.tracker.DTO;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "customer")
public class Customer{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @OneToMany(targetEntity = Orderr.class)
    private Set<Orderr> orders = new HashSet<>();

    public Customer(long id, String firstName,String lastName){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Customer(){
    }

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public Set<Orderr> getOrders(){
        return orders;
    }

    public void setOrders(Set<Orderr> orders){
        this.orders = orders;
    }

    @Override
    public String toString(){
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
