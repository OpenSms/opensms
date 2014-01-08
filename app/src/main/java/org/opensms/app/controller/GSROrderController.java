package org.opensms.app.controller;

import org.opensms.app.db.entity.Batch;
import org.opensms.app.db.entity.Customer;
import org.opensms.app.db.entity.GsrOrder;
import org.opensms.app.db.entity.User;
import org.opensms.app.db.service.CustomerDAOService;
import org.opensms.app.db.service.GsrOrderDAOService;
import org.opensms.app.view.entity.GsrOrderModel;
import org.opensms.app.view.model.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by dewmal on 1/3/14.
 */
@Controller
@RequestMapping(value = "/gsrorder")
public class GSROrderController {


    @Autowired
    private GsrOrderDAOService gsrOrderDAOService;

    @Autowired
    private CustomerDAOService customerDAOService;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "/save",method = RequestMethod.PUT)
    public ResponseMessage createGsrOrder(@RequestBody GsrOrderModel gsrOrderModel){
        Long id=gsrOrderDAOService.save(gsrOrderModel);
        return new ResponseMessage(ResponseMessage.Type.success,"ok");
    }


    /**
     * Get all gsr orders of currently logged user (customer)
     * @return
     */
    @RequestMapping(value = "/all/current/customer", method = RequestMethod.GET)
    public @ResponseBody List<GsrOrder> getAllGsrOrdersOfCurrentCustomer() {

        User user = (User) request.getSession().getAttribute("user");
        Customer customer = customerDAOService.getCustomer(user.getUserId());

        if (customer == null) {
            return new ArrayList<GsrOrder>();
        }

        return gsrOrderDAOService.getAllGsrOrdersOfCurrentCustomer(customer);
    }
}
