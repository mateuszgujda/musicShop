package com.onlineShop.Shop.security;

import com.onlineShop.Shop.Model.Cart;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MySuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    public MySuccessHandler() {
        super();
        this.setUseReferer(false);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        if(request.getSession().getAttribute("cart") == null)
            request.getSession().setAttribute("cart",new Cart());
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
