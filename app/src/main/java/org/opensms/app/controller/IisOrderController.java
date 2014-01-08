package org.opensms.app.controller;

import org.apache.log4j.Logger;
import org.opensms.app.db.entity.Employee;
import org.opensms.app.db.entity.IisOrder;
import org.opensms.app.db.entity.User;
import org.opensms.app.db.service.EmployeeDAOService;
import org.opensms.app.db.service.IisOrderDAOService;
import org.opensms.app.view.model.IisOrderModel;
import org.opensms.app.view.model.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    private static final Logger LOGGER = Logger.getLogger(IisOrderController.class);

    @Autowired
    private IisOrderDAOService iisOrderDAOService;
    @Autowired
    private EmployeeDAOService employeeDAOService;
    @Autowired
    private HttpServletRequest httpServletRequest;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage saveIisOrder(@RequestBody IisOrderModel iisOrderModel) {
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        Employee issuerEmployee = employeeDAOService.getEmployee(user.getUserId());

        if (issuerEmployee == null) {
            return new ResponseMessage(ResponseMessage.Type.error, "cannot save iis order.");
        }

        iisOrderModel.getIisOrder().setItemIssuerEmployee(issuerEmployee);
        iisOrderDAOService.saveIisOrder(iisOrderModel);
        return new ResponseMessage(ResponseMessage.Type.success, "iis order saved.");
    }


    @RequestMapping(value = "/all", params = {"empid"})
    @ResponseBody
    public List<IisOrder> getEmployeeRelatedIisOrders(@RequestParam(value = "empid") String empid) {
        return iisOrderDAOService.getAll(empid);
    }


    @RequestMapping(value = "/all/today", method = RequestMethod.GET)
    @ResponseBody
    public List<IisOrder> getTodaysIisOrders() {

        return iisOrderDAOService.getTodaysIisOrders();
    }


    @RequestMapping(value = "/all/today/open", method = RequestMethod.GET)
    @ResponseBody
    public List<IisOrder> getTodaysOpenIisOrders() {
        return iisOrderDAOService.getTodaysOpenIisOrders();
    }

    @RequestMapping(value = "/close", params = {"orderid"},method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage closeIISOrder(@RequestParam("orderid") String orderid) {
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        iisOrderDAOService.finishOrder(orderid, user);
        return new ResponseMessage(ResponseMessage.Type.success, "finish");

    }

}
