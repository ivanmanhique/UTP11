package org.tracker;

import org.tracker.DTO.Customer;
import org.tracker.DTO.OrderItem;
import org.tracker.DTO.Orderr;
import org.tracker.Repositories.*;


public class Main{
    public static void main(String[] args){

        CustomerRepository customerRepository = new CustomerRepository();
        OrderRepository orderRepository = new OrderRepository();
        ProductRepository productRepository = new ProductRepository();
        OrderItemRepository orderItemRepository = new OrderItemRepository();
        VipCustomerRepository vipCustomerRepository = new VipCustomerRepository();



        //orderItemRepository.addProduct(202l,productRepository.find(3l));
        //orderItemRepository.addOrder(152,orderRepository.find(102l));

        //vipCustomerRepository.creatingVipCustomerW5Percent();
        vipCustomerRepository.creatingVipCustomerW10Percent();

    }
}