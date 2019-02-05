package com.onlineShop.Shop.Model;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Cart is an entity that  holds {@link Product} until and {@link Order} is placed in the database
 *
 * @author  Mateusz Gujda
 */
@Component
@Scope("session")
public class Cart {

    /**
     * Object that later is added to the database
     */
    private Order order;

    /**
     * Price of all of the products
     */
    private double price;

    /**
     *
     * @return Count of all products
     */
    public int getItemCount() {
        return itemCount;
    }

    /**
     * Sets item number in  the cart
     * @param itemCount number to which the number of items is set to
     */
    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    /**
     * variable that holds number of products in the cart
     */
    private int itemCount;

    /**
     * Default constructor
     */
    public Cart(){
        order = new Order();
        price = 0;
        itemCount = 0;
    }

    /**
     * Method sets the price to the price param
     * @param price Param to which the price variable is set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     *
     * @return Method returns the  price variable value
     */
    public double getPrice() {
        return price;
    }

    /**
     *
     * @return Method returns the order object placed in the cart
     */
    public Order getOrder() {
        return order;
    }

    /**
     * Setter for the order variable
     * @param order order to set to
     */
    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     * Method handles seting the price based on number of items bought
     */
    public void sumUpCart(){
        price= 0;
        itemCount=0;
        for(OrderDetails orderDetails : order.getOrderDetailsSet()){
            price = price+(orderDetails.getAmount()*orderDetails.getProducts().getPrice());
            itemCount = itemCount+ orderDetails.getAmount();
        }
    }

    /**
     * Method that adds the product to the order variable
     * @param productToAdd product that is added
     * @param amount qunatity of the product
     * @param orderToAdd orderNumber to add
     */
    public void addToCart(Product productToAdd, int amount, Order orderToAdd){
        order.getOrderDetailsSet().add(new OrderDetails(productToAdd,amount, orderToAdd));
    }
}
