package com.onlineShop.Shop.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name="orderDetails")
public class OrderDetails {

    @Id
    @Column(name = "details_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id")
    private Order order;

    @OneToMany(mappedBy = "orderDetails")
    private Set<Product> products;

    @Column(name="amount")
    private  int amount;

}
