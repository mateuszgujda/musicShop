package com.onlineShop.Shop.Model;

import lombok.Data;

import javax.persistence.*;

/**
 * Role is an entity that helps to differentiate {@link User} by their permissions e.g Admin has role "ADMIN" so he has acces to some sites where the user with role "USER" won't have access
 * @author Mateusz Gujda
 */
@Data
@Entity
@Table(name = "role")
public class Role {

    /**
     * Unique role identifier
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private int id;

    /**
     * Name of the role like "ADMIN" or "USER"
     */
    @Column(name = "role")
    private String role;

    /**
     * Default id getter
     * @return Returns role id
     */
    public int getId() {
        return id;
    }

    /**
     * Default id setter
     * @param id the int to which the id will be set
     */
    public void setId(int id) {

        this.id = id;
    }

    /**
     * Default role getter
     * @return returns the String of the role name
     */
    public String getRole() {
        return role;
    }

    /**
     * Default role setter
     * @param role the String to which the role will set
     */
    public void setRole(String role) {
        this.role = role;
    }
}