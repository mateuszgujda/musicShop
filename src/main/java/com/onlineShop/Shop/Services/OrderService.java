package com.onlineShop.Shop.Services;

import com.onlineShop.Shop.Model.Order;
import com.onlineShop.Shop.Model.User;
import com.onlineShop.Shop.Repositories.OrderDetailsRepository;
import com.onlineShop.Shop.Repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Service("orderService")
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    @Autowired
    OrderDetailsService orderDetailsService;

    public Order findOrderById(int id){
        return orderRepository.findById(id);
    }

    public List<Order> findOrderByUsername(User user){

        return orderRepository.findByUser(user);
    }
    public void saveOrder(Order orderToSave){
        orderRepository.save(orderToSave);
    }


    public List<Order> getAllOrders(){
        List<Order> orders = orderRepository.findAll();
        for(Order order : orders){
            order.setOrderDetailsSet(orderDetailsRepository.findByOrder(order));
        }
        return orders;
    }

    public void updateOrder(Order orderToSave,int id){
        Order orderToUpdate = orderRepository.getOne(id);
        orderToUpdate.setTown(orderToSave.getTown());
        orderToUpdate.setPostal(orderToSave.getPostal());
        orderToUpdate.setAddress(orderToSave.getAddress());
        orderToUpdate.setStatus(orderToSave.getStatus());

        orderRepository.save(orderToUpdate);
    }
}
