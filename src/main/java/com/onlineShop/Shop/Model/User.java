package com.onlineShop.Shop.Model;

import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.LinkedList;
import java.util.Set;


/**
 * User is an entity that provides information about users form the database
 * @author  Mateusz Gujda
 *
 */
@Data
@Entity
@Table(name = "users")
public class User {
    public User() {
    }

    /**
     * Unique user identifier
     */
    @Id
    @Column(name= "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    /**
     * Username of the user by which the user can login
     */
    @Column(name = "username")
    @NotEmpty(message = "*Please provide username")
    private String username;

    /**
     * Variable that holds the user password
     */
    @Column(name = "password")
    @Length(min = 5, message = "*Your password must have at least 5 characters")
    @NotEmpty(message = "*Please provide your password")
    private String password;

    /**
     * Variable that holds the user email
     */
    @Column(name = "email")
    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
    private String email;

    /**
     * Variable that holds info if the account is enabled
     */
    @Column(name = "enabled")
    private int enabled;

    /**
     * The roles that the user has e.g "ADMIN" or "USER"
     */
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name="role_id"))
    private Set<Role> roles;


    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id")
    private Set<Order> orders;

    /**
     * Default id getter
     * @return users id
     */
    public int getId() {
        return id;
    }

    /**
     * Default id setter
     * @param id int to which the user id will be set
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Default enabled getter
     * @return returns the enabled value
     */
    public int getEnabled() {
        return enabled;
    }

    /**
     * Default enabled setter
     * @param enabled int to which the enabled will be set
     */
    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    /**
     * Default Roles getter
     * @return Set of all user Roles
     */
    public Set<Role> getRoles() {
        return roles;
    }

    /**
     * Default email getter
     * @return users email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Default password getter
     * @return user password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Default username getter
     * @return user's username
     */
    public String getUsername() {
        return username;
    }


    /**
     * Default email setter
     * @param email the string to which the user's email will be set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Default password setter
     * @param password string to which the user's password will be set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Default roles setter
     * @param roles set to which the user's roles will be set
     */
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    /**
     * Default username setter
     * @param username string to which the user's username will be set
     */
    public void setUsername(String username) {
        this.username = username;
    }
}
