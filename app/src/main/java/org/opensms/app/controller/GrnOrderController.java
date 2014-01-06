package org.opensms.app.controller;

import org.opensms.app.db.controller.impl.EmployeeDAOController;
import org.opensms.app.db.entity.Employee;
import org.opensms.app.db.entity.GrnOrder;
import org.opensms.app.db.entity.User;
import org.opensms.app.db.entity.Vendor;
import org.opensms.app.db.service.EmployeeDAOService;
import org.opensms.app.db.service.GrnOrderDAOService;
import org.opensms.app.db.service.VendorDAOService;
import org.opensms.app.view.model.GrnOrderModel;
import org.opensms.app.view.model.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

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
    private VendorDAOService vendorDAOService;

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

    @RequestMapping(method = RequestMethod.GET, params = {"grnorderid"})
    public @ResponseBody GrnOrder getGrnOrder(@RequestParam("grnorderid") Long grnOrderId) {
        return grnOrderDAOService.getGrnOrder(grnOrderId);
    }

    /**
     * Get all grn orders of currently logged user (vendor)
     * @return
     */
    @RequestMapping(value = "/all/current/vendor", method = RequestMethod.GET)
    public @ResponseBody List<GrnOrder> getAllGrnOrdersOfCurrentVendor() {

        User user = (User) request.getSession().getAttribute("user");
        Vendor vendor = vendorDAOService.getVendor(user.getUserId());

        if (vendor == null) {
            return new ArrayList<GrnOrder>();
        }

        return grnOrderDAOService.getAllGrnOrdersOfCurrentVendor(vendor);
    }
}
