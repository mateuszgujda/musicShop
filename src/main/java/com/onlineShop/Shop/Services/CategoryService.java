package com.onlineShop.Shop.Services;

import com.onlineShop.Shop.Model.Category;
import com.onlineShop.Shop.Repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Service to manage our {@link Category} that we select via {@link CategoryRepository}
 * @author  Mateusz Gujda
 */
@Service("categoryService")
public class CategoryService {

    /**
     * The repository via which we select from the database
     */
    @Autowired
    CategoryRepository categoryRepository;

    /**
     * Category from the database getter
     * @param name Name of the cateogry by which we search in the database
     * @return the Category object that was found in the database
     */
    public Category getCategoryByName(String name){
        Category found =  categoryRepository.findByCategory(name);

        return found;
    }

    /**
     * Function gets all the categories in the database
     * @return Function returns List containing all categories
     */
    public List<Category> findAll(){
        List<Category> categories = categoryRepository.findAll();

        return categories;
    }

    public Category findByCategory_id(int category_id){
        Category found = categoryRepository.findById(category_id);

        return found;
    }

    public  void updateCategoryById(int id, Category  overwrite){
        Category toUpdate = categoryRepository.getOne(id);

        toUpdate.setCategory(overwrite.getCategory());
        categoryRepository.save(toUpdate);
    }

    public void addCategory(Category toAdd){
        categoryRepository.save(toAdd);
    }

    public void deleteCategoryById(int id){
        categoryRepository.deleteById(id);
    }

}
