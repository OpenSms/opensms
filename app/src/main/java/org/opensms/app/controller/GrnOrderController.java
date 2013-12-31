package org.opensms.app.controller;

import org.opensms.app.db.controller.impl.EmployeeDAOController;
import org.opensms.app.db.entity.Employee;
import org.opensms.app.db.entity.GrnOrder;
import org.opensms.app.db.entity.User;
import org.opensms.app.db.service.EmployeeDAOService;
import org.opensms.app.db.service.GrnOrderDAOService;
import org.opensms.app.view.model.GrnOrderModel;
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
 * User: dewmal
 * Date: 11/15/13
 * Time: 12:41 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(value = "/grnorder")
public class GrnOrderController {
    @Autowired
    private GrnOrderDAOService grnOrderDAOService;

    @Autowired
    private EmployeeDAOService employeeDAOService;

    @Autowired
    private HttpServletRequest request;


    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage save(@RequestBody GrnOrderModel grnOrder){

        //If Login details are ok then save logged user in Http Session
        User user = (User) request.getSession().getAttribute("user");

        System.out.println(user);
        Employee employee=employeeDAOService.getEmployee(user.getId());


        GrnOrder order=new GrnOrder();
        order.setVendor(grnOrder.getVendor());


        order.setDataEntryEmployee(employee);

        grnOrderDAOService.save(order,grnOrder.getBatchList());

        return new ResponseMessage(ResponseMessage.Type.success,"saved");
    }
}
