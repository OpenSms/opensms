package org.opensms.app.controller;

import org.opensms.app.db.entity.User;
import org.opensms.app.db.service.UserDAOService;
import org.opensms.app.view.model.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
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
     * @return user id
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody Integer saveUser(@RequestBody User user) {
        Integer userId = userDAOService.saveUser(user);

        return userId;
    }

    /**
     * Validate user password
     * @param user
     * @return
     */
    @RequestMapping(value = "/validatepassword", method = RequestMethod.POST)
    public @ResponseBody boolean validatePassword(@RequestBody User user) {
        return userDAOService.validatePassword(user);
    }

    @RequestMapping(value = "/changepassword", method = RequestMethod.POST)
    public @ResponseBody ResponseMessage changePassword(@RequestBody User user) {
        userDAOService.changePassword(user);
        return new ResponseMessage(ResponseMessage.Type.success, "changePassword()");
    }

    /**
     * Change user account state
     * @param user
     * @return
     */
    @RequestMapping(value = "/updatestate", method = RequestMethod.POST)
    public @ResponseBody ResponseMessage updateUserAccountState(@RequestBody User user) {
        userDAOService.updateUserAccountState(user);

        return new ResponseMessage(ResponseMessage.Type.success, "updateUserAccountState()");
    }

    /**
     * Get user when user id is given
     * @param userId
     * @return User object
     */
    @RequestMapping(method = RequestMethod.GET, params = {"userId"})
    public @ResponseBody User getUser(@RequestParam("userId") Integer userId) {
        return userDAOService.getUser(userId);
    }

    /**
     * Get user when username is given
     * @param username
     * @return User object
     */
    @RequestMapping(method = RequestMethod.GET, params = {"username"})
    public
    @ResponseBody
    User getUser(@RequestParam("username") String username) {
        return userDAOService.getUser(username);
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
     *Search user by user id, username, name, email, city and country...
     *
     *
     * @param query
     * @return  list of users that meet the search criteria
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET,params = {"query"})
    public @ResponseBody List<User> search(@RequestParam("query") String query) {
        return userDAOService.search(query);
    }



    /**
     *Search user by user id, username, name, email, city and country...
     * for given type
     *
     *
     * @param query
     * @return  list of users that meet the search criteria
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET,params = {"query","type"})
    public @ResponseBody List<User> searchTypedUser(@RequestParam("query") String query,@RequestParam("type") String type) {
        return userDAOService.search(query,type);
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
        else if(user.getAccountStatus() == false){
            return new ResponseMessage(ResponseMessage.Type.error,"deactivated user.");
        }

         //If Login details are ok then save logged user in Http Session
        request.getSession().setAttribute("user",user);

        return new ResponseMessage(ResponseMessage.Type.success,"Valid Login Details");
    }
}
