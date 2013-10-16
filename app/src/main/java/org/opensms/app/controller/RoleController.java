package org.opensms.app.controller;

import org.opensms.app.db.entity.Role;
import org.opensms.app.db.service.UserDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sadika
 * Date: 10/13/13
 * Time: 2:51 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(value = "/role")
public class RoleController {
    @Autowired
    private UserDAOService userDAOService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody List<Role> getAllRoles() {
        return userDAOService.getAllRoles();
    }
}
