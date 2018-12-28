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
import org.springframework.util.FileSystemUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.util.LinkedList;
import java.util.List;

/**
 * MainController is a class that handles all requests
 *
 * @author Mateusz Gujda
 */

@RestController
public class MainController {
    /**
     * JPA service that enables getting {@link com.onlineShop.Shop.Model.User} and {@link com.onlineShop.Shop.Model.Role} information from database
     */
    @Autowired
    UserService userService;

    /**
     * JPA service that enables getting {@link com.onlineShop.Shop.Model.Product} information from database
     */
    @Autowired
    ProductService productService;
    /**
     * JPA service that enables getting {@link com.onlineShop.Shop.Model.Category} information from database
     */
    @Autowired
    CategoryService categoryService;

    /**
     * Method handles the /products mapping and returns list of  {@link com.onlineShop.Shop.Model.Product} based on {@link com.onlineShop.Shop.Model.Category}
     * @param model holds information about attribute that we pass
     * @param category holds information about what category to choose from
     * @return function returns the products template from thymeleaf templates folder
     */
    @RequestMapping(value = "/products",params = {"category"}, method = RequestMethod.GET)
    ModelAndView products(Model model, @RequestParam("category") String category){
        ModelAndView products =  new ModelAndView("products");

        List<Product> productLinkedList = productService.getProductsByCategory(category);

        model.addAttribute("category",category);
        model.addAttribute("products",productLinkedList);
        return products;
    }

    /**
     * Method handles displaying the main page
     * @return  returns the index template from thymeleaf folder
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    ModelAndView index(){
        ModelAndView index= new ModelAndView("index");
        return index;
    }

    /**
     * Method handles displaying the login page
     * @return returns the login template from thymleaf folder
     */
    @RequestMapping(value="/login",method = RequestMethod.GET)
    ModelAndView login(){
        ModelAndView login = new ModelAndView("login");
        return login;
    }


    /**
     * Method handles displaying the register form page
     * @param model helds information  about errors during applying the register form
     * @return returns the register template from thymeleaf folder
     */
    @RequestMapping(value="/register", method= RequestMethod.GET)
    public ModelAndView register(Model model){
        ModelAndView register = new ModelAndView();
        model.addAttribute("user",new User());
        return register;
    }

    /**
     * Method handles new user register by taking it from {@link #register(Model)} and adding to database via {@link UserService}
     * @param user user is a {@link com.onlineShop.Shop.Model.User} object containing information from form
     * @param result result has informtion about errors and adds it to the model
     * @return function retrns a view model which is a mapping to {@link #register(Model)} method with result parameters
     */
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


    /**
     * Function handles displaying /admin mapping
     * @return function returns adminPanel template from resources/templates folder
     */
    @RequestMapping(value="/admin",method = RequestMethod.GET)
    public  ModelAndView adminPanel(){
        ModelAndView model = new ModelAndView("admin/adminPanel");

        return model;
    }

    /**
     *Function handles displaying all users for administration purposes
     * Function finds all {@link User} from the database  adds them to the model
     * @return returns userPanel template frome resoucres/templates folder
     */
    @RequestMapping(value="/admin/users",method = RequestMethod.GET)
    public ModelAndView usersPanel(){
        ModelAndView model = new ModelAndView("admin/users");
        List<User> users =  userService.findAll();
        model.addObject("users",users);

        return model;
    }

    /**
     * Function handles displaying all categories for administration purposes
     * @return function returns categoriesPanel template from resources/templates folder
     */
    @RequestMapping(value="/admin/categoriesPanel",method = RequestMethod.GET)
    public ModelAndView categoriesPanel(){
        ModelAndView model = new ModelAndView("admin/categoriesPanel");

        return model;
    }

    /**
     * Function handles displaying {@link Product} edit form
     * @param id id is a parameter by which we select the {@link Product} to edit
     * @param fromAdd fromAdd model has errors that comes from {@link #addPhoto(int, MultipartFile, RedirectAttributes)}
     * @return returns the editProduct template from resources/templates folder
     */
    @RequestMapping(value="/admin/productsPanel/{id}",method = RequestMethod.GET)
    public ModelAndView productEditPanel(@PathVariable int id, Model fromAdd){
        ModelAndView model = new ModelAndView("admin/editProduct");
        String error =(String) fromAdd.asMap().get("error");
        String nofile= (String) fromAdd.asMap().get("nofile");
        String filename = "/static/img/products/"+id;
        File directory = new File(getClass().getResource(filename).getFile());
        model.addObject("files",directory.listFiles());
        Product product = productService.getProductByID(id);
        model.addObject("product",product);
        if(error != null){
            model.addObject("error",error);
        }
        if(nofile != null){
            model.addObject("nofile",nofile);
        }

        return model;
    }

    /**
     * Handles deliting {@link Product} from the database
     * @param id id is a parameter by which we select which {@link Product} to delete
     * @return Function redirects to productPanel {@link #productsPanel()}
     */
    @RequestMapping(value="/admin/productsPanel/{id}/delete",method = RequestMethod.GET )
    public ModelAndView deleteProduct(@PathVariable int id){

        String fileName = "/static/img/products/"+id;
        File file = new File(getClass().getResource(fileName).getFile());


        if (file.getAbsoluteFile().exists()) {
            if (file.isDirectory()) {
                FileSystemUtils.deleteRecursively(file);
                productService.deleteProductByID(id);
            }
        }

        return new ModelAndView("redirect:/admin/productsPanel");
    }

    /**
     * Function handles deleting photos from resources folder
     * @param id id is a parameter by which the product is chosen
     * @param num num is a parameter that tells which number is the photo numer to remove
     * @return
     */
    @RequestMapping(value="/admin/productsPanel/{id}/photos/{num}/delete",method = RequestMethod.GET)
    public ModelAndView deletePhoto(@PathVariable int id ,@PathVariable int num){
        String fileName = "/static/img/products/"+id+"/"+num+".jpg";
        File img_toDelete = new File(getClass().getResource(fileName).getFile());
        FileSystemUtils.deleteRecursively(img_toDelete);

        return new ModelAndView("redirect:/admin/productsPanel/"+id);
    }

    /**
     * Function handles uploading  photos to resources
     * @param id parameter that identifies the product to which we add photos
     * @param photo file that we chosen in the form
     * @param redirectAttributes eventual errors that we pass
     * @return function redirects us to {@link #editProduct(Product, int)};
     */
    @RequestMapping(value="/admin/productsPanel/{id}/photos/add",method=RequestMethod.POST)
    public ModelAndView addPhoto(@PathVariable int id, @RequestParam("photo") MultipartFile photo, RedirectAttributes redirectAttributes){
        String directoryName= "/static/img/products/"+id;
        File directory = new File(getClass().getResource(directoryName).getFile());
        File[] files= directory.listFiles();
        int num = files.length+1;
        if(photo.isEmpty()){
            redirectAttributes.addFlashAttribute("nofile","Select file to upload");
            return new ModelAndView("redirect:/admin/productsPanel/"+id);       }
        try {
            File uploaded = new File(getClass().getResource(directoryName).getFile()+ "/" + num + ".jpg");
            if(uploaded.createNewFile()){
                FileOutputStream fos = new FileOutputStream(uploaded);
                fos.write(photo.getBytes());

                fos.close();
            }

        } catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error","Something went wrong");
        }

        return new ModelAndView("redirect:/admin/productsPanel/"+id);
    }

    /**
     * Function handles overwriting product in the database with the product from form
     * @param product_to_edit product which was created in the form
     * @param id id of the product to overwrite
     * @return function redirects us to {@link #productsPanel()}
     */
    @RequestMapping(value="/admin/productsPanel/{id}/edit",method = RequestMethod.POST)
    public ModelAndView editProduct(@Valid Product product_to_edit,@PathVariable int id){
        Product exists = productService.getProductByID(id);
                if(exists != null){
                    productService.updateProductByID(id,product_to_edit);
                }


        return new ModelAndView("redirect:/admin/productsPanel");
    }

    /**
     * Function handles displaying all products from the database for administration purposes
     * @return function returns productsPanel generated template from resources/template folder
     */
    @RequestMapping(value="/admin/productsPanel",method = RequestMethod.GET)
    public ModelAndView productsPanel(){
        ModelAndView model = new ModelAndView("admin/productsPanel");
        List<Product> products = productService.getAllProducts();
        model.addObject("products",products);

        return model;
    }

    @RequestMapping(value="/admin/categoriesPanel/{id}", method = RequestMethod.GET)
    public  ModelAndView categoryEditPanel(@PathVariable int id){
        ModelAndView model = new ModelAndView("admin/editCategory");
        Category category = categoryService.findByCategory_id(id);
        model.addObject("category",category);

        return model;
    }

    @RequestMapping(value ="/admin/categoriesPanel/{id}/edit",method = RequestMethod.POST)
    public  ModelAndView editCategory( @ModelAttribute("name") Category toEdit ,@PathVariable int id){
        Category exists = categoryService.findByCategory_id(id);
        if(exists !=  null){
            categoryService.updateCategoryById(id,toEdit);
        }

        return new ModelAndView("redirect:/admin/categoriesPanel");
    }

    @RequestMapping(value = "/admin/categoriesPanel/{id}/delete",method = RequestMethod.GET)
    public ModelAndView deleteCategory(@PathVariable int id){
        categoryService.deleteCategoryById(id);

        return new ModelAndView("redirect:/admin/categoriesPanel");
    }

    /**
     *Function that enables us to access all categories in Thymeleaf templates
     * @return returns all Categories from the database
     */
    @ModelAttribute("categories")
    public List<Category> categories() {
        return categoryService.findAll();
    }
}
