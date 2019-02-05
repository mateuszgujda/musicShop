package com.onlineShop.Shop.Model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.*;

/**
 * Order is an entity that  holds {@link OrderDetails } in which is holded information about product and its amount details
 *
 * @author  Mateusz Gujda
 */
@Data
@Entity
@Table(name="orders")
public class Order{

    /**
     * Unique identifier for the order
     */
    @Id
    @NotNull
    @Column(name="order_id")
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private  int id;

    /**
     * Status of an order
     */
    @Column(name = "status")
    private String  status;


    /**
     * Date of completing the order
     */
    @Column(name="order_date")
    private Date date;


    /**
     * Address of the delivery
     */
    @Column(name="address")
    @NotEmpty(message = "Please provide an adress")
    private String address;

    /**
     * Post code of the delivery
     */
    @Column(name="postal")
    @NotEmpty(message="Please provide postal code")
    @Pattern(message="Format of postal code like 00-123  ", regexp = "(^[0-9]{2}-[0-9]{3}$)")
    private String postal;

    /**
     * Town to deliver to
     */
    @Column(name="town")
    @NotEmpty(message = "Please provide town to deliver")
    private String town;

    /**
     * Postal variable getter
     * @return postal variable
     */
    public String getPostal() {
        return postal;
    }

    /**
     * Setter for postal
     * @param postal param to which the postal variable is set
     */
    public void setPostal(String postal) {
        this.postal = postal;
    }

    /**
     * Getter for town
     * @return Town variable
     */
    public String getTown() {
        return town;
    }

    /**
     * Variable setter
     * @param town String that the variable is set to
     */
    public void setTown(String town) {
        this.town = town;
    }

    /**
     * Getter for price
     * @return returns price of an order
     */
    public double getPrice() {
        return price;
    }

    /**
     * Price setter
     * @param price The price of the order is setted to this value
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Price of an order
     */
    @Column(name="price")
    private double price;

    /**
     * Identifier getter
     * @return id of an order
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for an id
     * @param id value to which id is set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for status
     * @return status of the variable
     */
    public String getStatus() {
        return status;
    }

    /**
     * Setter for the status variable
     * @param status Value to which the status is set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Getter for the date
     * @return returns the date variable
     */
    public Date getDate() {
        return date;
    }

    /**
     * Setter for the date variable
     * @param date value to which the date variable is set to
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Getter for the address variable
     * @return value of the address variable
     */
    public String getAddress() {
        return address;
    }

    /**
     * Setter for the address variable
     * @param address value to which the address is set to
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Getter for the user variable
     * @return returns the user variable value
     */
    public User getUser() {
        return user;
    }


    /**
     * Setter for the user variable
     * @param user the value to which the user variable is set to
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Getter for the orderDetailsSet variable
     * @return value of the orderDetailsSet
     */
    public Set<OrderDetails> getOrderDetailsSet() {
        return orderDetailsSet;
    }

    /**
     * Setter for the orderDetailsSet variable
     * @param orderDetailsSet value to which the orderDetailsSet is set to
     */
    public void setOrderDetailsSet(Set<OrderDetails> orderDetailsSet) {
        this.orderDetailsSet = orderDetailsSet;
    }

    /**
     * Default constructor
     */
    public Order(){
        orderDetailsSet = new HashSet<>();
    }

    /**
     * user variable
     */
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    /**
     * set of order details
     */
    @OneToMany(mappedBy = "order",cascade=CascadeType.ALL)
    private  Set<OrderDetails> orderDetailsSet;



}