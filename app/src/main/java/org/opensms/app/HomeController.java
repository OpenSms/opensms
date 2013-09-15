/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opensms.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

/**
 * @author dewmalpc
 */
@Controller
public class HomeController {

    private static final Logger LOG = Logger.getLogger(HomeController.class.getName());


    @Autowired
    private HttpServletRequest context;
    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String homeForm(Model model) {
        LOG.info("Welcome home!");
        return "index";
    }



}
