package com.onlineShop.Shop.Services;

import com.onlineShop.Shop.Model.Role;
import com.onlineShop.Shop.Model.User;
import com.onlineShop.Shop.Repositories.RoleRepository;
import com.onlineShop.Shop.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.onlineShop.Shop.security.SecurityConfig;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;


/**
 * Service to manage our {@link User} that we select via {@link UserRepository}
 * @author  Mateusz Gujda
 */
@Service("userService")
public class UserService implements UserDetailsService {

    /**
     * Repository that handles our database user selecing
     */
    private UserRepository userRepository;

    /**
     * Repository that handles our database category selecting
     */
    private RoleRepository roleRepository;

    /**
     *  Object that helps us to encode and decode the password
     */
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Constructor of the repository
     * @param userRepository the userRepository to which we set the userRepository
     * @param roleRepository the RoleRepository to which we sete the roleRepository
     * @param bCryptPasswordEncoder our password coder and encoder
     */
    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    /**
     * Function that handles selecting user from the databse by username
     * @param username string by which we select the user
     * @return User object that has been selected from the datbase
     */
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Function tat handles selecting all users from the database
     * @return List object containing all users from the database
     */
    public List<User> findAll(){
        List<User> users = userRepository.findAll();

        return users;
    }

    /**
     * Function that handles saving a new created user to the database
     * @param user User object that we receive from the registration form {@link com.onlineShop.Shop.Controllers.MainController#registerUser(User, BindingResult)}
     */
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setEnabled(1);
        Role userRole = roleRepository.findByRole("USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    /**
     * Function that handles overwriting the user information
     * @param user Object that holds information to overwrite to
     */
    public void updateUser(User user){
       User toUpdate= userRepository.getOne(user.getUsername());
        toUpdate.setEmail(user.getEmail());

        userRepository.save(toUpdate);
    }

    /**
     * Function that load user by Username not done because {@link CategoryService} handles login
     * @param s username
     * @return null
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }

}
