package com.onlineShop.Shop.Model;

public class Product {
    private String product_id;
    private String model;
    private String producer;
    private String description;
    private String category;
    private Integer amount;
    private Double price;

    public Product(String product_id, String model, String producer, String description, String category, Integer amount, Double price){
        this.product_id=product_id;
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

    public String getCategory() {
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

    public String getProduct_id() {
        return product_id;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setCategory(String category) {
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

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }
}
