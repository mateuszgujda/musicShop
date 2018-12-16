package com.onlineShop.Shop.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="categories")
public class Category {
    @Id
    @Column(name="category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int category_id;
    @Column(name="category")
    private String category;

    @OneToMany(mappedBy ="category")
    private List<Product> products;

    public Category(int category_id, String category){
        this.category_id = category_id;
        this.category = category;
    }

    public  Category(){
    }


    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }
}
