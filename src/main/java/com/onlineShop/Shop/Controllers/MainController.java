package com.onlineShop.Shop.Controllers;

import com.onlineShop.Shop.Model.Category;
import com.onlineShop.Shop.Model.Product;
import com.onlineShop.Shop.Model.User;
import com.onlineShop.Shop.Services.CategoryService;
import com.onlineShop.Shop.Services.ProductService;
import com.onlineShop.Shop.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
public class MainController {

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "/products",params = {"category"}, method = RequestMethod.GET)
    ModelAndView products(Model model, @RequestParam("category") String category){
        ModelAndView products =  new ModelAndView("products");

        List<Product> productLinkedList = productService.getProductsByCategory(category);

        model.addAttribute("category",category);
        model.addAttribute("products",productLinkedList);
        return products;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    ModelAndView index(){
        ModelAndView index= new ModelAndView("index");
        return index;
    }

    @RequestMapping(value="/login",method = RequestMethod.GET)
    ModelAndView login(){
        ModelAndView login = new ModelAndView("login");
        return login;
    }




    @RequestMapping(value="/register", method= RequestMethod.GET)
    public ModelAndView register(Model model){
        ModelAndView register = new ModelAndView();
        model.addAttribute("user",new User());
        return register;
    }

    @RequestMapping(value="/register", method= RequestMethod.POST)
    public ModelAndView registerUser(@Valid User user, BindingResult result){
        ModelAndView model = new ModelAndView();
        User userExists = userService.findUserByUsername(user.getUsername());
        if(userExists != null){
            result.rejectValue("username","error.user","There is already a user with this username");
        }
        if(result.hasErrors()){
           model.setViewName("register");
        } else{
            userService.saveUser(user);
            model.addObject("successMessage","User has been registered succesfully");
            model.addObject("user", new User());
            model.setViewName("register");
        }
        return model;
    }


    @RequestMapping(value="/admin",method = RequestMethod.GET)
    public  ModelAndView adminPanel(){
        ModelAndView model = new ModelAndView("admin/adminPanel");

        return model;
    }

    @RequestMapping(value="/admin/users",method = RequestMethod.GET)
    public ModelAndView usersPanel(){
        ModelAndView model = new ModelAndView("admin/users");
        List<User> users =  userService.findAll();
        model.addObject("users",users);

        return model;
    }

    @RequestMapping(value="/admin/categoriesPanel",method = RequestMethod.GET)
    public ModelAndView categoriesPanel(){
        ModelAndView model = new ModelAndView("/admin/categoriesPanel");

        return model;
    }

    @RequestMapping(value="/admin/productsPanel",method = RequestMethod.GET)
    public ModelAndView productsPanel(){
        ModelAndView model = new ModelAndView("/admin/productsPanel");
        List<Product> products = productService.getAllProducts();
        model.addObject("products",products);

        return model;
    }

    @ModelAttribute("categories")
    public List<Category> categories() {
        return categoryService.findAll();
    }
}
