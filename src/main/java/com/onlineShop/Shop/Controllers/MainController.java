package com.onlineShop.Shop.Controllers;

import com.onlineShop.Shop.products.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.onlineShop.Shop.database.*;

import java.util.LinkedList;
import java.util.List;

@RestController
public class MainController {


    @RequestMapping(value = "/products",params = {"category"}, method = RequestMethod.GET)
    ModelAndView products(Model model, @RequestParam("category") String category){
        ModelAndView products =  new ModelAndView("products");
        DB database = new DB();
        List<Product> productLinkedList = database.getPorductsByCategory(category);

        model.addAttribute("category",category);
        model.addAttribute("products",productLinkedList);
        return products;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    ModelAndView index(){
        ModelAndView index= new ModelAndView("index");
        return index;
    }

}
