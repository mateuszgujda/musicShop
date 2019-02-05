package com.onlineShop.Shop.Repositories;

import com.onlineShop.Shop.Model.Order;
import com.onlineShop.Shop.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {

    Order findById(int id);

    List<Order> findByUser(User user);

    Order findByDate(Date date);

    Order deleteById(int id);

    Order findByStatus(String status);


}
