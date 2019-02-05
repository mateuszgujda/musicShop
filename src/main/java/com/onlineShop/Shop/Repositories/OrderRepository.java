package com.onlineShop.Shop.Repositories;

import com.onlineShop.Shop.Model.Order;
import com.onlineShop.Shop.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * Interface that enables to retrieve Order from the database
 * @author Mateusz Gujda
 */
public interface OrderRepository extends JpaRepository<Order,Integer> {

    /**
     * Function that retrives Order based by the identifier
     * @param id param by which the order is chosen from the database
     * @return Order object retrieved from the databased
     */
    Order findById(int id);

    /**
     * Function retrieves List of orders belonging to an User
     * @param user param by which the orders are chosen from the database
     * @return List object conatining Order objects from the database
     */
    List<Order> findByUser(User user);

    /**
     * Function retrieves Order based by date
     * @param date param by whichc the orders are chosen from the database
     * @return Order objects based by date
     */
    List<Order> findByDate(Date date);

    /**
     * Function deletes order based by id;
     * @param id param by which the order to delete is chosen
     */
    void deleteById(int id);

    /**
     * Returns list of orders based by their status
     * @param status param by which the orders are shown
     * @return The list of all orders with the status param
     */
    List<Order> findByStatus(String status);


}
