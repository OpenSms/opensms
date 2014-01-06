/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opensms.app;

import org.apache.log4j.Logger;
import org.opensms.app.db.entity.User;
import org.opensms.app.db.entity.UserRole;
import org.opensms.app.db.service.UserDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.security.Principal;
import java.util.List;

/**
 * @author dewmalpc
 */
@Controller
public class HomeController {

    private static final Logger LOG = Logger.getLogger(HomeController.class.getName());
    @Autowired
    UserDAOService userDAOService;
    @Autowired
    private HttpServletRequest context;

    /**
     * Home View
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String homeForm(Principal principal) {
        if (principal != null) {
            User activeUser = (User) ((Authentication) principal).getPrincipal();
            LOG.info(activeUser);
        }
        LOG.info("Welcome home!");
        if (context.getSession().getAttribute("user") != null) {
            return "home";
        }
        return "login";
    }

    /**
     * Log in page.
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm() {
        LOG.info("Welcome home!");
        if (context.getSession().getAttribute("user") != null) {
            return "home";
        }
        return "login";
    }

    @RequestMapping(value = "/attendance", method = RequestMethod.GET)
    public String attendenceForm() {
        return "attendance";
    }

    /**
     * Login Error
     */
    @RequestMapping(value = "/login_error", method = RequestMethod.GET)
    public String errorForm() {
        LOG.info("Welcome home!");
        if (context.getSession().getAttribute("user") != null) {
            return "home";
        }
        return "errorPages/login_error";
    }

    /**
     * Get currently logged user.
     *
     * @return User
     */
    @RequestMapping(value = "/currentuser", method = RequestMethod.GET)
    public
    @ResponseBody
    User getCurrentUser() {
        return (User) context.getSession().getAttribute("user");
    }

    /**
     * Get current
     *
     * @return List<UserRole>
     */
    @RequestMapping(value = "/currentuserroles", method = RequestMethod.GET)
    public
    @ResponseBody
    List<UserRole> getUserRoles() {
        User user = (User) context.getSession().getAttribute("user");
        return userDAOService.getUserRoles(user.getUserId());
    }

    @RequestMapping(value = "/isUserLoggedIn", method = RequestMethod.GET)
    public
    @ResponseBody
    boolean isUserLoggedIn() {
        return context.getSession().getAttribute("user") != null;
    }
}
