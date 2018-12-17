package com.onlineShop.Shop.Repositories;

import com.onlineShop.Shop.Model.Category;
import com.onlineShop.Shop.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    Product findByModel(String model);
    List<Product> findByCategory(Category category);

    Product findById(int id);

    void deleteById(int id);

}
