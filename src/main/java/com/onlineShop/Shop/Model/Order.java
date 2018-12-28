package com.onlineShop.Shop.Model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;


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


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy = "order")
    Set<OrderDetails> orderDetailsSet;



}
