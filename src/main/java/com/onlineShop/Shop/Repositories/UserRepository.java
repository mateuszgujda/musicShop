package com.onlineShop.Shop.Repositories;
import com.onlineShop.Shop.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Interface that enables to get information about Users from the database
 * @author  Mateusz Gujda
 */
public interface UserRepository extends JpaRepository<User, String> {
    /**
     * Function that searches for the user in the database via username
     * @param username the sting by which we select the user
     * @return User object that was found in the database
     */
    User findByUsername(String username);
}