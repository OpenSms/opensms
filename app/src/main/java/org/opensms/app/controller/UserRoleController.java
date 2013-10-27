package org.opensms.app.controller;

import org.opensms.app.db.entity.UserRole;
import org.opensms.app.db.service.UserDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sadika
 * Date: 10/26/13
 * Time: 5:58 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(value = "/userrole")
public class UserRoleController {
    @Autowired
    UserDAOService userDAOService;

    @RequestMapping(method = RequestMethod.GET, params = {"userId"})
    public @ResponseBody List<UserRole> getUserRoles(@RequestParam("userId") Integer userId) {
        return userDAOService.getUserRoles(userId);
    }
}
