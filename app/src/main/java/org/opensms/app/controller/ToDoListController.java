package org.opensms.app.controller;

import org.opensms.app.db.entity.IisOrder;
import org.opensms.app.db.entity.PreOrder;
import org.opensms.app.db.entity.UserContactDetail;
import org.opensms.app.db.service.IisOrderDAOService;
import org.opensms.app.db.service.PreOrderDAOService;
import org.opensms.app.view.entity.Customer;
import org.opensms.app.view.entity.TripData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by dewmal on 12/31/13.
 */
@Controller
@RequestMapping(value = "/todo")
public class ToDoListController {

    @Autowired
    private IisOrderDAOService iisOrderDAOService;

    @Autowired
    private PreOrderDAOService preOrderDAOService;

    @Autowired
    private ContactDetailsController contactDetailsController;


    @RequestMapping(value = "/list", method = RequestMethod.GET, params = {"sales_person"})
    public
    @ResponseBody
   TripData getAll(@RequestParam(value = "sales_person") String sales_person) {
        TripData tripData = new TripData();
        IisOrder iisOrder = iisOrderDAOService.getOpenOrder(sales_person);
        tripData.setIssue_emp_id(iisOrder.getItemIssuerEmployee().getId());
        List<PreOrder> preOrderList = iisOrder.getPreOrderList();
        for (PreOrder preOrder :preOrderList) {
            Customer customer = new Customer();
            org.opensms.app.db.entity.Customer customer1 = preOrder.getCustomer();
            UserContactDetail contactDetails = contactDetailsController.getContactDetails(customer1.getUserId());
            customer.setCustomerDetails(customer1, contactDetails);
            org.opensms.app.view.entity.PreOrder order = new org.opensms.app.view.entity.PreOrder();
            order.updatePreOrder(preOrder);
            tripData.addCustomer(customer);
            tripData.addPreOrder(order);
        }
        return tripData;
    }
}
