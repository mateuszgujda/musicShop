package com.onlineShop.Shop.Model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.*;


@Data
@Entity
@Table(name="orders")
public class Order{

    @Id
    @NotNull
    @Column(name="order_id")
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private  int id;

    @Column(name = "status")
    private String  status;


    @Column(name="order_date")
    private Date date;


    @Column(name="address")
    @NotEmpty(message = "Please provide an adress")
    private String address;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Column(name="price")
    private double price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<OrderDetails> getOrderDetailsSet() {
        return orderDetailsSet;
    }

    public void setOrderDetailsSet(Set<OrderDetails> orderDetailsSet) {
        this.orderDetailsSet = orderDetailsSet;
    }

    public Order(){
        orderDetailsSet = new HashSet<>();
    }

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy = "order")
    private  Set<OrderDetails> orderDetailsSet;



}