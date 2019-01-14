package com.onlineShop.Shop.Repositories;

import com.onlineShop.Shop.Model.Order;
import com.onlineShop.Shop.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface OrderRepository extends JpaRepository<Order,Integer> {

    Order findById(int id);

    Order findByDate(Date date);

    Order deleteById(int id);

    Order findByStatus(String status);

    Order findByUser(User user);

}
