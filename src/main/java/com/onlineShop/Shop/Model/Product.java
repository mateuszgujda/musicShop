package com.onlineShop.Shop.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;


/**
 * Entity that matches the product table from database
 * @author  Mateusz Gujda
 */
@Data
@Entity
@Table(name="products")
public class Product {
    /**
     * Unique identifier for each product
     */
    @Id
    @Column(name= "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Model of the product in our shop
     */
    @Column(name=" model")
    private String model;

    /**
     * Name of the producer of the product in our shop
     */
    @Column (name= "producer")
    private String producer;

    /**
     * Description of the product in shop
     */
    @Column (name = "description")
    private String description;

    /**
     * {@link Category} Joining the product table with Category table / Each product has one cateogry
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    /**
     * Quantity of our product
     */
    @Column(name="amount")
    private Integer amount;

    /**
     * Price of our product
     */
    @Column (name="price")
    private Double price;


    /**
     * Default constructor
     */
    public Product(){

    }

    /**
     * Parametered constructor
     * @param product_id This will be the products product_id
     * @param model This will be the products model
     * @param producer This will be products producer
     * @param description This will be products description
     * @param category This will be products Category
     * @param amount This will be products amount
     * @param price This will be products price
     */
    public Product(int product_id, String model, String producer, String description, Category category, Integer amount, Double price){
        this.id=product_id;
        this.model=model;
        this.producer=producer;
        this.description=description;
        this.category=category;
        this.amount=amount;
        this.price=price;
    }

    /**
     * Default price getter
     * @return price of the product
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Default amount getter
     * @return returns quantity of the product
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * Default category getter
     * @return Category of the product
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Default description getter
     * @return Description of the product
     */
    public String getDescription() {
        return description;
    }

    /**
     * Default model getter
     * @return Model of the product
     */
    public String getModel() {
        return model;
    }

    /**
     * Default producer getter
     * @return Producer of the product
     */
    public String getProducer() {
        return producer;
    }

    /**
     * Default product_id getter
     * @return returns id of the product
     */
    public int getProduct_id() {
        return id;
    }

    /**
     * Default amount setter
     * @param amount the value to which will be set the amount of product
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * Default Category setter
     * @param category Category to which the product's Category will be set
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * Default description setter
     * @param description String to which the description will be set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Default model setter
     * @param model String to which the model will be set
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Default price setter
     * @param price Double to which the model will be set
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Default producer setter
     * @param producer String to which the producer will be set
     */
    public void setProducer(String producer) {
        this.producer = producer;
    }

    /**
     * Default id setter
     * @param product_id int to wchich the id will be set
     */
    public void setProduct_id(int product_id) {
        this.id = product_id;
    }

    /**
     * Function that provides info about instance of an object
     * @return String that contains all fields of the Object instance
     */
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", producer='" + producer + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", amount=" + amount +
                ", price=" + price +
                '}';
    }
}
