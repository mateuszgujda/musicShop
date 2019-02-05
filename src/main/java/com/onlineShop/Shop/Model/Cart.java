package com.onlineShop.Shop.Model;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class Cart {

    private Order order;

    private double price;

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    private int itemCount;

    public Cart(){
        order = new Order();
        price = 0;
        itemCount = 0;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void sumUpCart(){
        price= 0;
        itemCount=0;
        for(OrderDetails orderDetails : order.getOrderDetailsSet()){
            price = price+(orderDetails.getAmount()*orderDetails.getProducts().getPrice());
            itemCount = itemCount+ orderDetails.getAmount();
        }
    }

    public void addToCart(Product productToAdd, int amount, Order orderToAdd){
        order.getOrderDetailsSet().add(new OrderDetails(productToAdd,amount, orderToAdd));
    }
}
