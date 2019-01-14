package com.onlineShop.Shop.Services;

import com.onlineShop.Shop.Model.Order;
import com.onlineShop.Shop.Model.User;
import com.onlineShop.Shop.Repositories.OrderDetailsRepository;
import com.onlineShop.Shop.Repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    public Order findOrderById(int id){
        return orderRepository.findById(id);
    }

    public List<Order> findOrderByUsername(User user){

        return findOrderByUsername(user);
    }

    public void saveOrder(Order orderToSave){
        orderRepository.save(orderToSave);
    }

}
