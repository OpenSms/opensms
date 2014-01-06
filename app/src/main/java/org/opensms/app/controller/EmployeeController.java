package org.opensms.app.controller;

import org.opensms.app.db.entity.Employee;
import org.opensms.app.db.entity.User;
import org.opensms.app.db.entity.UserRole;
import org.opensms.app.db.service.EmployeeDAOService;
import org.opensms.app.view.model.EmployeeModel;
import org.opensms.app.view.model.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sadika
 * Date: 10/16/13
 * Time: 2:48 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(value = "/employee")
public class EmployeeController {
    @Autowired
    private EmployeeDAOService employeeDAOService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody ResponseMessage saveEmployee(@RequestBody EmployeeModel employeeModel) {
        employeeDAOService.saveEmployee(employeeModel.getEmployee(), employeeModel.getRoles());

        return new ResponseMessage(ResponseMessage.Type.success, "employee save");
    }

    @RequestMapping(value = "/updatenames", method = RequestMethod.POST)
    public @ResponseBody ResponseMessage updateEmployee(@RequestBody Employee employee) {
        employeeDAOService.updateEmployee(employee);

        return  new ResponseMessage(ResponseMessage.Type.success, "employee names updated");
    }

    /**
     * Updates user roles of a user.
     * List of UserRoles are needed
     * @param userRoles
     * @return
     */
    @RequestMapping(value = "/updateroles", method = RequestMethod.POST)
    public @ResponseBody ResponseMessage updateEmployeeRoles(@RequestBody List<UserRole> userRoles) {
        employeeDAOService.updateEmployeeRoles(userRoles);

        return new ResponseMessage(ResponseMessage.Type.success, "employee roles updated");
    }

    @RequestMapping(method = RequestMethod.GET, params = {"userId"})
    public @ResponseBody Employee getEmployee(@RequestParam("userId") Integer userId) {
        return employeeDAOService.getEmployee(userId);
    }

    /**
     * Employee attendence
     * @param user
     * @return
     */
    @RequestMapping(value = "/attendance/signin", method = RequestMethod.POST)
    public @ResponseBody ResponseMessage attendanceSignin(@RequestBody User user) {
//        employeeDAOService.updateEmployeeRoles(userRoles);

        return new ResponseMessage(ResponseMessage.Type.success, "employee attendance signin - success");
    }
}
