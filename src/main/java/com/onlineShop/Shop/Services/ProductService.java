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

/**
 * Class that handles getting {@link Product} via {@link ProductRepository} from the database
 *
 */
@Service("productService")
public class ProductService{

    /**
     *  Repository from which we get products from the database
     */
    @Autowired
    private ProductRepository productRepository;

    /**
     * Repository from which we get categories from the database
     */
    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * Function that handle selecting all products
     * @return List of all Products from the database
     */
    public List<Product> getAllProducts(){
        List<Product> products = new LinkedList<>();
        products = productRepository.findAll();

        return products;
    }

    /**
     * Function that handles selecting all products in the same category
     * @param category category name by which we select products
     * @return List of products that have category given in the function argument
     */
    public List<Product> getProductsByCategory(String category){
        Category category_to_check = categoryRepository.findByCategory(category);
        List<Product> products = new LinkedList<>();
        products = productRepository.findByCategory(category_to_check);

        return products;
    }

    /**
     * Function that handles selectin product by ID
     * @param id int by which we select the product
     * @return Product object  found in the database
     */
    public Product getProductByID(int id){

        return productRepository.findById(id);
    }

    /**
     * Function that handles deleting product by ID
     * @param id int by which we select the product to delete
     */
    public void deleteProductByID(int id) {
        productRepository.deleteById(id);
    }

    /**
     * Function that updates the product in te database by ID
     * @param id int by which we select the product to update
     * @param product_to_edit Product we receive from the edit form {@link com.onlineShop.Shop.Controllers.MainController#editProduct(Product, int)}
     */
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

    /**
     * Function that handles adding product to the database
     * @param toAdd Product object to add to the database
     */
    public  void addProduct(Product toAdd){
        productRepository.save(toAdd);
    }

}
