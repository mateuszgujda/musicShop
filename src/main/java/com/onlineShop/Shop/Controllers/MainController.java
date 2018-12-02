package com.onlineShop.Shop.Controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MainController {

    @Value("${spring.application.name}")
    String appName;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    ModelAndView products(Model model){
        ModelAndView products =  new ModelAndView("products");
        model.addAttribute("appName",appName);
        return products;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    ModelAndView index(){
        ModelAndView index= new ModelAndView("index");
        return index;
    }

}
