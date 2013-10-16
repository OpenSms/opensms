package org.opensms.app.controller;

import org.opensms.app.db.entity.Customer;
import org.opensms.app.db.service.CustomerDAOService;
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
 * Date: 10/14/13
 * Time: 9:35 AM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(value = "/customer")
public class CustomerController {
    @Autowired
    private CustomerDAOService customerDAOService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody ResponseMessage saveCustomer(@RequestBody Customer customer) {
        customerDAOService.saveCustomer(customer);

        return new ResponseMessage(ResponseMessage.Type.success, "customer save");
    }
}
