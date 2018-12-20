package com.onlineShop.Shop.Repositories;

import com.onlineShop.Shop.Model.Category;
import com.onlineShop.Shop.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Interface that enables to retrieve product information from database
 * @author  Mateusz Gujda
 */
public interface ProductRepository extends JpaRepository<Product,Integer> {
    /**
     * Function that retrieves product from the database by model
     * @param model string by which we search for the product
     * @return returns Product object with parameters from the database
     */
    Product findByModel(String model);

    /**
     * Retrieves all products from the database based on the category
     * @param category category by which we select products
     * @return List of products that have the same category
     */
    List<Product> findByCategory(Category category);

    /**
     * Find the product from the database by id
     * @param id int by which we search for the product
     * @return returns the product with the id
     */
    Product findById(int id);

    /**
     * Deletes the product by id
     * @param id int by which we select the product to be deleted
     */
    void deleteById(int id);

}
