package com.onlineShop.Shop.Services;


import com.onlineShop.Shop.Model.Order;
import com.onlineShop.Shop.Model.OrderDetails;
import com.onlineShop.Shop.Repositories.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Class that handles getting {@link OrderDetails} via {@link OrderDetailsRepository} from the database
 *
 */
@Service("orderDetailsService")
public class OrderDetailsService {

    /**
     * Variable that holds the information about repository
     */
    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    /**
     * Function that handles saving Set of Order Details to the database
     * @param orderDetailsSet
     */
    public void saveOrderDetailsSet(Set<OrderDetails> orderDetailsSet){
        for(OrderDetails details : orderDetailsSet){
            orderDetailsRepository.save(details);
        }
    }

    /**
     * Function that gives all orderDetails belonging to an order
     * @param order Order by which the Details are selected
     * @return Set object of OrderDetails belonging to order param
     */
    public Set<OrderDetails> findDetailsByOrder(Order order){

        return orderDetailsRepository.findByOrder(order);
    }

    /**
     * Return OrderDetails from the database
     * @param id param by which the order is retrieved from the database
     * @return OrderDetails object found in the database
     */
    public OrderDetails findDetailsById(int id){
        return orderDetailsRepository.findById(id);
    }

}
