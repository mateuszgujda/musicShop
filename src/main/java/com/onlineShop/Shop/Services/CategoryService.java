package com.onlineShop.Shop.Services;

import com.onlineShop.Shop.Model.Category;
import com.onlineShop.Shop.Repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service("categoryService")
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public Category getCategoryByName(String name){
        Category found =  categoryRepository.findByCategory(name);

        return found;
    }

    public List<Category> findAll(){
        List<Category> categories = categoryRepository.findAll();

        return categories;
    }
}
