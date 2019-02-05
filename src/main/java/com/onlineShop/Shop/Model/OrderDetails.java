package com.onlineShop.Shop.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * OrderDetails is an entity that  holds {@link Product } information and qunatity of bought product
 *
 * @author  Mateusz Gujda
 */
@Data
@EqualsAndHashCode(exclude="order")
@Entity
@Table(name="order_details")
public class OrderDetails {

    /**
     * Unique identifier
     */
    @Id
    @Column(name = "details_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Order to which the details belongs to
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id")
    private Order order;

    /**
     * Product that is holded in orderDetail
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product")
    private Product product;

    /**
     * Quantity of the product
     */
    @NotNull
    @Column(name="amount")
    private  int amount;

    /**
     * Identifier getter
     * @return value of the identifier
     */
    public int getId() {
        return id;
    }

    /**
     * Identifier setter
     * @param id the value to which the id is set to
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Order variable getter
     * @return value of the order variable
     */
    public Order getOrder() {
        return order;
    }

    /**
     * Default constructor
     */
    public OrderDetails(){};

    /**
     * Parametric constructor
     * @param product product to which the product variable is set
     * @param amount quantity of the product
     * @param order order that owns the details
     */
    public OrderDetails(Product product, int amount,Order order){
        this.product = product;
        this.amount = amount;
        this.order= order;
    }

    /**
     * Order setter
     * @param order the value to which the order is set to
     */
    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     * Product getter
     * @return value of the product variable
     */
    public Product getProducts() {
        return product;
    }

    /**
     * Product variable setter
     * @param product value to which the product is set to
     */
    public void setProducts(Product product) {
        this.product = product;
    }

    /**
     * Returns the quantity of the product saved in details
     * @return
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Amount setter
     * @param amount value to which the amount is set to
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }
}
