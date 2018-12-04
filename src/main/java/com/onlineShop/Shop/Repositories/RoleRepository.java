package com.onlineShop.Shop.Repositories;

import com.onlineShop.Shop.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRole(String role);

}
