package org.tracker.DTO;

import jakarta.persistence.*;

@Entity
public class VipCustomer{

    @Id
    private long id;

    @Column(name = "discountRate")
    private double discountRate;

    public VipCustomer(){
    }


    public double getDiscountRate(){
        return discountRate;
    }

    public void setDiscountRate(double discountRate){
        this.discountRate = discountRate;
    }

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

}
