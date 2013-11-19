package org.opensms.app.controller;

import org.opensms.app.db.entity.Employee;
import org.opensms.app.db.entity.User;
import org.opensms.app.db.service.EmployeeDAOService;
import org.opensms.app.db.service.IisOrderDAOService;
import org.opensms.app.view.model.IisOrderModel;
import org.opensms.app.view.model.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: sadika
 * Date: 11/16/13
 * Time: 1:02 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(value = "/iisorder")
public class IisOrderController {
    @Autowired
    private IisOrderDAOService iisOrderDAOService;

    @Autowired
    private EmployeeDAOService employeeDAOService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody ResponseMessage saveIisOrder(@RequestBody IisOrderModel iisOrderModel) {

        User user = (User) httpServletRequest.getSession().getAttribute("user");
        Employee issuerEmployee = employeeDAOService.getEmployee(user.getUserId());

        iisOrderModel.getIisOrder().setItemIssuerEmployee(issuerEmployee);

        iisOrderDAOService.saveIisOrder(iisOrderModel);

        return new ResponseMessage(ResponseMessage.Type.success, "iis order saved.");
    }
}
