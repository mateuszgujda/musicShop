package com.onlineShop.Shop.security;

import com.onlineShop.Shop.Model.Cart;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * MySuccessHandler class handles successful user login
 *
 * @author  Mateusz Gujda
 */
@Component
public class MySuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    /**
     * Default constructor
     */
    public MySuccessHandler() {
        super();
        this.setUseReferer(false);
    }

    /**
     * Function that handles what happens after succesfull login
     * @param request Object that hold request attribute infromation
     * @param response Object that holds response attribute information
     * @param authentication Oject that holds authentication information
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        if(request.getSession().getAttribute("cart") == null)
            request.getSession().setAttribute("cart",new Cart());
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
