package com.onlineShop.Shop.Repositories;

import com.onlineShop.Shop.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface that enables to retrieve information from the database about the Roles
 * @author Mateusz Gujda
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {
    /**
     * Function finds role by name in the database
     * @param role string by which we search for the role
     * @return Role object that has info from the database
     */
    Role findByRole(String role);

}
