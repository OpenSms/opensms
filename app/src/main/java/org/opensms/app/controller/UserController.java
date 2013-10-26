package org.opensms.app.controller;

import org.opensms.app.db.entity.Role;
import org.opensms.app.db.entity.User;
import org.opensms.app.db.service.UserDAOService;
import org.opensms.app.view.model.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private HttpServletRequest request;

    /**
     * Save user
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody Integer saveUser(@RequestBody User user) {
        Integer userId = userDAOService.saveUser(user);

        return userId;
    }

    /**
     * Get all users
     *
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody List<User> getAll() {
         return userDAOService.getAll();
    }


    /**
     *
     * @param query
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public @ResponseBody List<User> search(@RequestParam("query") String query) {
        return userDAOService.search(query);
    }


    /**
     * Controller For Login Management
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public @ResponseBody ResponseMessage login(@RequestBody User user){

        user=userDAOService.login(user);
        if(user==null){
            return new ResponseMessage(ResponseMessage.Type.error,"invalid login details");
        }

         //If Login details are ok then save logged user in Http Session
        request.getSession().setAttribute("user",user);


        return new ResponseMessage(ResponseMessage.Type.success,"Valid Login Details");
    }
}
