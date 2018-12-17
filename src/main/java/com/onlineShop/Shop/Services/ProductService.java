package com.onlineShop.Shop.Services;


import com.onlineShop.Shop.Model.Category;
import com.onlineShop.Shop.Model.Product;
import com.onlineShop.Shop.Repositories.CategoryRepository;
import com.onlineShop.Shop.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
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

    public void deleteProductByID(int id) {
        productRepository.deleteById(id);
    }

    public void updateProductByID(int id,Product product_to_edit){
        Product product_to_update= productRepository.getOne(id);
       product_to_update.setModel(product_to_edit.getModel());
       product_to_update.setProducer(product_to_edit.getProducer());
       product_to_update.setDescription(product_to_edit.getDescription());
       product_to_update.setPrice(product_to_edit.getPrice());
       product_to_update.setCategory(product_to_edit.getCategory());
       product_to_update.setAmount(product_to_edit.getAmount());

        productRepository.save(product_to_update);
    }
}
