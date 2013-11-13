package org.opensms.app.controller;

import org.opensms.app.db.entity.Customer;
import org.opensms.app.db.entity.User;
import org.opensms.app.db.service.CustomerDAOService;
import org.opensms.app.db.service.PreOrderDAOService;
import org.opensms.app.view.model.PreOrderModel;
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
 * Date: 11/6/13
 * Time: 9:10 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(value = "/preorder")
public class PreOrderController {

    @Autowired
    PreOrderDAOService preOrderDAOService;

    @Autowired
    private CustomerDAOService customerDAOService;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody ResponseMessage savePreOrder(@RequestBody PreOrderModel preOrderModel) {

        User user = (User) request.getSession().getAttribute("user");
        Customer customer = customerDAOService.getCustomer(user.getUserId());

        preOrderModel.getPreOrder().setCustomer(customer);

        preOrderDAOService.savePreOrder(preOrderModel.getPreOrder(), preOrderModel.getPreOrderHasItemList());

        return new ResponseMessage(ResponseMessage.Type.success, "pre-order saved.");
    }
}
