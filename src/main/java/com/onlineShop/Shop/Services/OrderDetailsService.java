package com.onlineShop.Shop.Services;


import com.onlineShop.Shop.Model.Order;
import com.onlineShop.Shop.Model.OrderDetails;
import com.onlineShop.Shop.Repositories.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service("orderDetailsService")
public class OrderDetailsService {

    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    public void saveOrderDetailsSet(Set<OrderDetails> orderDetailsSet){
        for(OrderDetails details : orderDetailsSet){
            orderDetailsRepository.save(details);
        }
    }

    public Set<OrderDetails> findDetailsByOrder(Order order){

        return orderDetailsRepository.findByOrder(order);
    }

}
