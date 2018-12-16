package com.onlineShop.Shop.Services;


import com.onlineShop.Shop.Model.Category;
import com.onlineShop.Shop.Model.Product;
import com.onlineShop.Shop.Repositories.CategoryRepository;
import com.onlineShop.Shop.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service("productService")
public class ProductService{
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Product> getAllProducts(){
        List<Product> products = new LinkedList<>();
        products = productRepository.findAll();

        return products;
    }

    public List<Product> getProductsByCategory(String category){
        Category category_to_check = categoryRepository.findByCategory(category);
        List<Product> products = new LinkedList<>();
        products = productRepository.findByCategory(category_to_check);

        return products;
    }

    public Product getProductByID(int id){

        return productRepository.findById(id);
    }

}
