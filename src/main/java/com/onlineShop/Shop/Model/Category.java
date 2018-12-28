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
    private int id;

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
     * @param id id that will be set
     * @param category category name that will be set
     */
    public Category(int id, String category){
        this.id = id;
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
                "id=" + id +
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
     * Default id getter
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Default id setter
     * @param id id which will be set
     */
    public void setId(int id) {
        this.id = id;
    }
}
