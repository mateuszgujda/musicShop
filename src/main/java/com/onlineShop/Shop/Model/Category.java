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
    @Column(name="categoryID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryID;

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
     * @param categoryID categoryID that will be set
     * @param category category name that will be set
     */
    public Category(int categoryID, String category){
        this.categoryID = categoryID;
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
                "categoryID=" + categoryID +
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
     * Default categoryID getter
     * @return categoryID
     */
    public int getCategoryID() {
        return categoryID;
    }

    /**
     * Default categoryID setter
     * @param categoryID categoryID which will be set
     */
    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }
}
