package org.opensms.app.controller;

import org.opensms.app.db.entity.Customer;
import org.opensms.app.db.entity.User;
import org.opensms.app.db.entity.UserContactDetail;
import org.opensms.app.db.service.ContactDetailsDAOService;
import org.opensms.app.db.service.CustomerDAOService;
import org.opensms.app.db.service.UserDAOService;
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

    @Autowired
    private ContactDetailsDAOService daoService;


    @Autowired
    private UserDAOService userDAOService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody ResponseMessage saveCustomer(@RequestBody Customer customer) {
        customerDAOService.saveCustomer(customer);
        return new ResponseMessage(ResponseMessage.Type.success, "customer save");
    }



    @RequestMapping(value = "/save", method = RequestMethod.PUT)
    public @ResponseBody ResponseMessage saveCustomer(@RequestBody org.opensms.app.view.entity.Customer customer) {

        Customer cus=new Customer();
        User user=new User();
        user.setPassword(customer.getNicNumber());
        user.setUsername(customer.getNicNumber());


        cus.setUser(user);
        cus.setName(customer.getFirstName());


        UserContactDetail userContactDetail=new UserContactDetail();
        userContactDetail.setName(customer.getFirstName());
        userContactDetail.setCity(customer.getLocation().getCity());
        userContactDetail.setAddressLine1(customer.getLocation().getStreet());
        userContactDetail.setProvince(customer.getLocation().getProvince());
        userContactDetail.setPostalCode(customer.getLocation().getPostalcode());


        Integer saveUser = userDAOService.saveUser(user);
        user.setUserId(saveUser);
        userContactDetail.setUser(user);
        daoService.saveContactDetails(userContactDetail);
        customerDAOService.saveCustomer(cus);


        return new ResponseMessage(ResponseMessage.Type.success, "customer save");
    }
}
