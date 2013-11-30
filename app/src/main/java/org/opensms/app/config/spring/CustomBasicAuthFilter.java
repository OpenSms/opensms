package org.opensms.app.config.spring;

import org.apache.log4j.Logger;
import org.opensms.app.db.entity.User;
import org.opensms.app.secure.CustomUserProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: dewmal
 * Date: 11/30/13
 * Time: 4:05 PM
 * To change this template use File | Settings | File Templates.
 */
@Component("customBasicAuthFilter")
public class CustomBasicAuthFilter implements AuthenticationSuccessHandler {

    private static final Logger LOGGER = Logger.getLogger(CustomBasicAuthFilter.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {




        User activeUser = (User) authentication.getPrincipal();
        LOGGER.info(activeUser);

        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("user",activeUser);

        httpServletResponse.sendRedirect("/");


    }
}
