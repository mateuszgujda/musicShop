package com.onlineShop.Shop.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="products")
public class Product {
    @Id
    @Column(name= "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name=" model")
    private String model;
    @Column (name= "producer")
    private String producer;
    @Column (name = "description")
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
    @Column(name="amount")
    private Integer amount;
    @Column (name="price")
    private Double price;

    public Product(){

    }

    public Product(int product_id, String model, String producer, String description, Category category, Integer amount, Double price){
        this.id=product_id;
        this.model=model;
        this.producer=producer;
        this.description=description;
        this.category=category;
        this.amount=amount;
        this.price=price;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getAmount() {
        return amount;
    }

    public Category getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getModel() {
        return model;
    }

    public String getProducer() {
        return producer;
    }

    public int getProduct_id() {
        return id;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public void setProduct_id(int product_id) {
        this.id = product_id;
    }

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
