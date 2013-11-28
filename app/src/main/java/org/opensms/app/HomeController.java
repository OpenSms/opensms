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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author dewmalpc
 */
@Controller
public class HomeController {

    private static final Logger LOG = Logger.getLogger(HomeController.class.getName());


    @Autowired
    private HttpServletRequest context;

    @Autowired
    UserDAOService userDAOService;

    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String homeForm(Model model) {
        LOG.info("Welcome home!");

        if(context.getSession().getAttribute("user")!=null){
            return "home";
        }

        return "index";
    }

    /**
     * Get currently logged user.
     * @return User
     */
    @RequestMapping(value = "/currentuser", method = RequestMethod.GET)
    public @ResponseBody User getCurrentUser() {
        return (User) context.getSession().getAttribute("user");
    }


    @RequestMapping(value = "/currentuserroles", method = RequestMethod.GET)
    public @ResponseBody List<UserRole> getUserRoles() {
        User user = (User) context.getSession().getAttribute("user");
        return userDAOService.getUserRoles(user.getUserId());
    }
}
