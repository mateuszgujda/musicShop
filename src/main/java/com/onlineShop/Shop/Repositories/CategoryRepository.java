package com.onlineShop.Shop.Repositories;

import com.onlineShop.Shop.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    Category findByCategory(String category);
}
