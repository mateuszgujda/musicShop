package com.onlineShop.Shop.Repositories;

import com.onlineShop.Shop.Model.Order;
import com.onlineShop.Shop.Model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

/**
 * Interface that enables to retrieve OrderDetails by name from the database
 * @author Mateusz Gujda
 */
public interface OrderDetailsRepository extends JpaRepository<OrderDetails,Integer> {
    /**
     * Returns the category Object based on the integer
     * @param id Identifier by which we select the OrderDetails
     * @return OrderDetails object based by the integer given in the parameter
     */
    OrderDetails findById(int id);

    /**
     * Returns the Set of all OrderDetails that belongs to the given Order
     * @param order Order that holds all OrderDetail s
     * @return Set of the OrderDetails
     */
    Set<OrderDetails> findByOrder(Order order);
}
