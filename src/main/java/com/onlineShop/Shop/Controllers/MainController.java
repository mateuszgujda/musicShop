package com.onlineShop.Shop.Controllers;

import com.onlineShop.Shop.Model.*;
import com.onlineShop.Shop.Services.CategoryService;
import com.onlineShop.Shop.Services.OrderService;
import com.onlineShop.Shop.Services.ProductService;
import com.onlineShop.Shop.Services.UserService;
import com.onlineShop.Shop.security.MySuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.ui.Model;
import org.springframework.util.FileSystemUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
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
import java.security.Principal;
import java.sql.Blob;
import java.util.*;

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

    @Autowired
    OrderService orderService;



    /**
     * Method handles the /products mapping and returns list of  {@link com.onlineShop.Shop.Model.Product} based on {@link com.onlineShop.Shop.Model.Category}
     * @param model holds information about attribute that we pass
     * @param category holds information about what category to choose from
     * @return function returns the products template from thymeleaf templates folder
     */
    @RequestMapping(value = "/products",params = {"category"}, method = RequestMethod.GET)
    ModelAndView products(Model model, @RequestParam("category") String category){
        ModelAndView products =  new ModelAndView("products");
        String success =(String) model.asMap().get("msg");
        List<Product> productLinkedList = productService.getProductsByCategory(category);

        model.addAttribute("success",success);
        model.addAttribute("category",category);
        model.addAttribute("products",productLinkedList);
        return products;
    }

    @RequestMapping(value="/products/{id}",method = RequestMethod.GET)
    ModelAndView productDescription(@PathVariable int id){
        ModelAndView model  = new ModelAndView("productDescription");
        Product product = productService.getProductByID(id);
        File directory = new File(getClass().getResource("/static/img/products/"+id).getFile());

        model.addObject("images",directory.listFiles());
        model.addObject("product",product);
        return model;
    }

    /**
     * Method handles displaying the main page
     * @return  returns the index template from thymeleaf folder
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    ModelAndView index(HttpServletRequest request,Model model){
        ModelAndView index= new ModelAndView("index");
        String added = (String)model.asMap().get("added");
        if(added != null){
            index.addObject("added",added);
        }

        return index;
    }

    /**
     * Method handles displaying the login page
     * @return returns the login template from thymleaf folder
     */
    @RequestMapping(value="/login",method = RequestMethod.GET)
    ModelAndView login(Model model, HttpServletRequest request){
        ModelAndView login = new ModelAndView("login");
        String referer = request.getHeader("Referer");


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

    @RequestMapping(value = "/admin/productsPanel/add",method = RequestMethod.GET)
    public ModelAndView addProduct(){
        ModelAndView model = new ModelAndView("admin/addProduct");
        model.addObject("product",new Product());

        return model;
    }

    @RequestMapping(value = "/admin/productsPanel/add",method = RequestMethod.POST)
    public ModelAndView saveProduct(@Valid Product toAdd){
        productService.addProduct(toAdd);
        String path = getClass().getResource("/static/img/products").getPath();
        File directory = new File(path+"/"+toAdd.getProduct_id());
        directory.mkdir();
        return new ModelAndView("redirect:/admin/productsPanel/"+toAdd.getProduct_id());
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

    @RequestMapping(value="/admin/categoriesPanel/add",method = RequestMethod.GET)
    public ModelAndView addCategory(){
        ModelAndView model = new ModelAndView("admin/addCategory");
        Category toAdd = new Category();
        model.addObject("toAdd",toAdd);

        return  model;
    }


    @RequestMapping(value="/admin/categoriesPanel/add", method = RequestMethod.POST)
    public  ModelAndView saveCategory(@ModelAttribute("name") Category toAdd){
        categoryService.addCategory(toAdd);

        return new ModelAndView("redirect:/admin/categoriesPanel");
    }

    @RequestMapping(value = "/admin/categoriesPanel/{id}/delete",method = RequestMethod.GET)
    public ModelAndView deleteCategory(@PathVariable int id){
        categoryService.deleteCategoryById(id);

        return new ModelAndView("redirect:/admin/categoriesPanel");
    }

    @RequestMapping(value = "/admin/ordersPanel")
    public ModelAndView ordersPanel(){
        ModelAndView model = new ModelAndView("admin/orderPanel");
        model.addObject("orders",orderService.getAllOrders());
        return model;
    }

    @RequestMapping(value="/admin/ordersPanel/{id}",method = RequestMethod.GET)
    public ModelAndView editOrder(@PathVariable int id){
        ModelAndView model = new ModelAndView("admin/editOrder");
        model.addObject("order",orderService.findOrderById(id));

        return model;
    }

    @RequestMapping(value="/admin/ordersPanel/{id}/edit",method = RequestMethod.POST)
    public ModelAndView orderChanges(@Valid Order orderToSave, @PathVariable int id){
        ModelAndView model = new ModelAndView("redirect:/admin/ordersPanel");
        orderService.updateOrder(orderToSave,id);

        return model;
    }


    @RequestMapping(value= "/cart", method = RequestMethod.GET)
    public ModelAndView viewCart(HttpServletRequest request){
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if(cart != null){
            ModelAndView model = new ModelAndView("cart");
            model.addObject("orderDetails",cart.getOrder().getOrderDetailsSet());
            model.addObject("price",cart.getPrice());

            return model;
        }
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value="/cart/add/{id}",method = RequestMethod.POST)
    public ModelAndView addToCart(@PathVariable int id, @RequestParam(value="quantity") int quantity, HttpServletRequest request, RedirectAttributes redirectAttributes){
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        Product toAdd = productService.getProductByID(id);
        cart.getOrder().getOrderDetailsSet().add(new OrderDetails(toAdd,quantity,cart.getOrder()));
        toAdd.setAmount(toAdd.getAmount()-quantity);
        productService.updateProductByID(id,toAdd);
        cart.sumUpCart();
        redirectAttributes.addFlashAttribute("msg","Product added to cart");



        return new ModelAndView("redirect:/products?category="+toAdd.getCategory().getCategory());
    }

    @RequestMapping(value="/cart/remove/{id}/{amount}",method = RequestMethod.GET)
    public ModelAndView removeFromCart(@PathVariable int id, @PathVariable int amount, HttpServletRequest request){
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        Product toUpdate = productService.getProductByID(id);
        Set<OrderDetails> temp = new HashSet(cart.getOrder().getOrderDetailsSet());
        cart.getOrder().getOrderDetailsSet().clear();
        for(OrderDetails orderDetails:  temp){
            if(orderDetails.getProducts().getProduct_id() != id){
               cart.getOrder().getOrderDetailsSet().add(orderDetails);
            }
        }
        toUpdate.setAmount(toUpdate.getAmount()+amount);
        productService.updateProductByID(id,toUpdate);
        cart.sumUpCart();
       return new ModelAndView("redirect:/cart");
    }


    @RequestMapping(value="/cart/checkout",method = RequestMethod.POST)
    public ModelAndView checkout(@Valid Order orderToAdd,BindingResult result, HttpServletRequest request, RedirectAttributes redirectAttributes, @AuthenticationPrincipal UserDetails currentUser){
        if(result.hasErrors()){
            ModelAndView model= new ModelAndView("checkout");

            return model;
        }

        Cart cart = (Cart) request.getSession().getAttribute("cart");
        cart.getOrder().setDate(new Date());
        cart.getOrder().setUser(userService.findUserByUsername(currentUser.getUsername()));
        cart.getOrder().setPrice(cart.getPrice());
        cart.getOrder().setStatus("Waiting to be paid");
        cart.getOrder().setAddress(orderToAdd.getAddress());
        cart.getOrder().setPostal(orderToAdd.getPostal());
        cart.getOrder().setTown(orderToAdd.getTown());
        orderService.saveOrder(cart.getOrder());
        redirectAttributes.addFlashAttribute("added","Order has been placed");
        cart.setOrder(new Order());
        cart.sumUpCart();
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/cart/checkout", method = RequestMethod.GET)
    public ModelAndView sumOrder(HttpServletRequest request){
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        ModelAndView model = new ModelAndView("checkout");
        model.addObject("order",cart.getOrder());

        return model;
    }

    @RequestMapping(value="/user", method = RequestMethod.GET)
    public ModelAndView userPanel(HttpServletRequest request){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.findUserByUsername(auth.getName());
        ModelAndView model = new ModelAndView("userPanel");
        model.addObject("user",currentUser);
        model.addObject("orders",orderService.findOrderByUsername(currentUser));

        return model;
    }

    @RequestMapping(value="/cancelOrder/{id}", method = RequestMethod.GET)
    public  ModelAndView cancelOrder(@PathVariable int id){
        Order toUpdate = orderService.findOrderById(id);
        toUpdate.setStatus("Aborted");
        orderService.updateOrder(toUpdate,id);

        return new ModelAndView("redirect:/user");
    }

    @RequestMapping(value="/user/edit", method= RequestMethod.POST)
    public ModelAndView editUserInfo(@Valid User user, BindingResult result){
        if(!result.hasErrors()){
            userService.updateUser(user);
        }

        return new ModelAndView("redirect:/user");
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
