package org.opensms.app.controller;

import org.opensms.app.db.entity.Employee;
import org.opensms.app.db.service.EmployeeDAOService;
import org.opensms.app.view.model.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public @ResponseBody ResponseMessage saveEmployee(@RequestBody Employee employee) {
        employeeDAOService.saveEmployee(employee);

        return new ResponseMessage(ResponseMessage.Type.success, "employee save");
    }
}
