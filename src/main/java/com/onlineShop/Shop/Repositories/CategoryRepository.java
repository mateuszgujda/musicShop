package com.onlineShop.Shop.Repositories;

import com.onlineShop.Shop.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface that enables to retrieve Category by name from the database
 * @author Mateusz Gujda
 */
public interface CategoryRepository extends JpaRepository<Category,Integer> {
    /**
     * Returns the category from the database based on the string
     * @param category category name by which we select the category
     * @return Category object from the database with the name given in the parameter
     */
    Category findByCategory(String category);
}
