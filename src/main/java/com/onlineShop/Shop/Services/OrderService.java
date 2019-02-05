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

/**
 * Class that handles getting {@link Order} via {@link OrderRepository} from the database
 *
 */
@Service("orderService")
public class OrderService {

    /**
     * variable that holds repository information
     */
    @Autowired
    OrderRepository orderRepository;

    /**
     * variable that holds orderDetails repository information
     */
    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    /**
     * variableat is connection to orderDetailsService
     */
    @Autowired
    OrderDetailsService orderDetailsService;

    /**
     * Function that retrieves the Order from the database
     * @param id param by which the Order to retrieve is chosen to the database
     * @return Order object retrieve from the database
     */
    public Order findOrderById(int id){
        return orderRepository.findById(id);
    }

    /**
     * Function that retrieves orders that belongs to the user
     * @param user param by which the orders are chosen
     * @return List object containing all Orders belonging to the user
     */
    public List<Order> findOrderByUsername(User user){

        return orderRepository.findByUser(user);
    }

    /**
     * Function that handles saving order in the database
     * @param orderToSave the Order object thats is added to the database
     */
    public void saveOrder(Order orderToSave){
        orderRepository.save(orderToSave);
    }

    /**
     * Function that returns all Orders from the database
     * @return List object containing all Order objects saved in the database
     */
    public List<Order> getAllOrders(){
        List<Order> orders = orderRepository.findAll();
        for(Order order : orders){
            order.setOrderDetailsSet(orderDetailsRepository.findByOrder(order));
        }
        return orders;
    }

    /**
     * Function that handles overwriting an order
     * @param orderToSave Order that holds information to overwrite
     * @param id param by which the order to overwrite is chosen
     */
    public void updateOrder(Order orderToSave,int id){
        Order orderToUpdate = orderRepository.getOne(id);
        orderToUpdate.setTown(orderToSave.getTown());
        orderToUpdate.setPostal(orderToSave.getPostal());
        orderToUpdate.setAddress(orderToSave.getAddress());
        orderToUpdate.setStatus(orderToSave.getStatus());

        orderRepository.save(orderToUpdate);
    }
}
