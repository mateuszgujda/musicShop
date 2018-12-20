package com.onlineShop.Shop.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Category is an entity that  divides {@link Product} into groups in databse
 *
 * @author  Mateusz Gujda
 */
@Data
@Entity
@Table(name="categories")
public class Category {

    /**
     * Unique ID that helps us identify a category
     */
    @Id
    @Column(name="category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int category_id;

    /**
     * Name of every category
     */
    @Column(name="category")
    private String category;

    /**
     * List of all products that has the same category
     */
    @OneToMany(mappedBy ="category")
    private List<Product> products;

    /**
     * Parametered constructor
     * @param category_id category_id that will be set
     * @param category category name that will be set
     */
    public Category(int category_id, String category){
        this.category_id = category_id;
        this.category = category;
    }

    /**
     * Default constructor
     */
    public  Category(){
    }

    /**
     * Function returns description of a Class in String values
     * @return string containing all info about instance of a Class
     */
    @Override
    public String toString() {
        return "Category{" +
                "category_id=" + category_id +
                ", category='" + category + '\'' +
                ", products=" + products +
                '}';
    }

    /**
     * Default setter for a category name
     * @param category name which will be set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Default getter for  category name
     * @return category name as String value
     */
    public String getCategory() {
        return category;
    }

    /**
     * Default category_id getter
     * @return category_id
     */
    public int getCategory_id() {
        return category_id;
    }

    /**
     * Default category_id setter
     * @param category_id category_id which will be set
     */
    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }
}
