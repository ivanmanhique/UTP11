package org.tracker.DTO;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class OrderItem{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "count")
    private int count;

    @ManyToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.PERSIST
    })
    private Orderr orderr;

    @OneToMany(targetEntity = Product.class)
    private Set<Product> product;

    public OrderItem(){
    }

    public OrderItem(long id, int count){
        this.id = id;
        this.count = count;
    }

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public int getCount(){
        return count;
    }

    public void setCount(int count){
        this.count = count;
    }

    public Orderr getOrderr(){
        return orderr;
    }

    public void setOrderr(Orderr orderr){
        this.orderr = orderr;
    }

    public Set<Product> getProduct(){
        return product;
    }

    public void setProduct(Set<Product> product){
        this.product = product;
    }
}
