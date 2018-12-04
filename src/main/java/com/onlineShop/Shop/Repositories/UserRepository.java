package com.onlineShop.Shop.Repositories;
import com.onlineShop.Shop.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
}