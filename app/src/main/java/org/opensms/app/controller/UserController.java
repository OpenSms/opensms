package org.opensms.app.controller;

import org.opensms.app.db.entity.Role;
import org.opensms.app.db.entity.User;
import org.opensms.app.db.service.UserDAOService;
import org.opensms.app.view.model.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sadika
 * Date: 10/9/13
 * Time: 5:02 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserDAOService userDAOService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody Integer saveUser(@RequestBody User user) {
        Integer userId = userDAOService.saveUser(user);

        return userId;
    }

    @RequestMapping(value = "/save_with_user_roles", method = RequestMethod.POST)
    public @ResponseBody Integer saveUser(@RequestBody User user, List<Role> roleList) {
        Integer userId = userDAOService.saveUser(user, roleList);

        return userId;
    }


}
