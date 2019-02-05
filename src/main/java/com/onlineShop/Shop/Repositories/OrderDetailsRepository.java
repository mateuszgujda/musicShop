package com.onlineShop.Shop.Repositories;

import com.onlineShop.Shop.Model.Order;
import com.onlineShop.Shop.Model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails,Integer> {
    OrderDetails findById(int id);
    Set<OrderDetails> findByOrder(Order order);
}
