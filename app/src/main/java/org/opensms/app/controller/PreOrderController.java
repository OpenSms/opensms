package org.opensms.app.controller;

import org.opensms.app.db.entity.Customer;
import org.opensms.app.db.entity.PreOrder;
import org.opensms.app.db.entity.User;
import org.opensms.app.db.service.CustomerDAOService;
import org.opensms.app.db.service.PreOrderDAOService;
import org.opensms.app.view.model.PreOrderModel;
import org.opensms.app.view.model.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

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
    public
    @ResponseBody
    ResponseMessage savePreOrder(@RequestBody PreOrderModel preOrderModel) {
        User user = (User) request.getSession().getAttribute("user");
        Customer customer = customerDAOService.getCustomer(user.getUserId());
        if (customer == null) {
            customer = customerDAOService.getCustomer(1);
        }
        preOrderModel.getPreOrder().setCustomer(customer);
        preOrderDAOService.savePreOrder(preOrderModel.getPreOrder(), preOrderModel.getPreOrderHasItemList());
        return new ResponseMessage(ResponseMessage.Type.success, "pre-order saved.");
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public
    @ResponseBody
    List<PreOrderModel> getAllPreOrders() {
        List<PreOrderModel> preOrderModelList = new ArrayList<PreOrderModel>();
        List<PreOrder> preOrderList = preOrderDAOService.getAll();
        for (PreOrder p : preOrderList) {
            PreOrderModel model = new PreOrderModel();
            model.setPreOrder(p);
            model.setPreOrderHasItemList(preOrderDAOService.getPreOrderHasItemsOf(p.getPreOrderId()));
            preOrderModelList.add(model);
        }
        return preOrderModelList;
    }

    @RequestMapping(value = "/all/open", method = RequestMethod.GET)
    public
    @ResponseBody
    List<PreOrderModel> getAllOpenPreOrders() {
        List<PreOrderModel> preOrderModelList = new ArrayList<PreOrderModel>();
        List<PreOrder> preOrderList = preOrderDAOService.getAllOpenPreOrders();
        for (PreOrder p : preOrderList) {
            PreOrderModel model = new PreOrderModel();
            model.setPreOrder(p);
            model.setPreOrderHasItemList(preOrderDAOService.getPreOrderHasItemsOf(p.getPreOrderId()));
            preOrderModelList.add(model);
        }
        return preOrderModelList;
    }

    @RequestMapping(value = "/at", method = RequestMethod.GET, params = {"location"})
    public
    @ResponseBody
    List<PreOrderModel> getPreOrdersAt(@RequestParam("location") String location) {
        List<PreOrderModel> preOrderModelList = new ArrayList<PreOrderModel>();
        List<PreOrder> preOrderList = preOrderDAOService.getPreOrdersAt(location);
        for (PreOrder p : preOrderList) {
            PreOrderModel model = new PreOrderModel();
            model.setPreOrder(p);
            model.setPreOrderHasItemList(preOrderDAOService.getPreOrderHasItemsOf(p.getPreOrderId()));
            preOrderModelList.add(model);
        }
        return preOrderModelList;
    }

    /**
     * Get all Preorder of current user (customer)
     * use for reports
     * @return
     */
    @RequestMapping(value = "/all/current/customer", method = RequestMethod.GET)
    public @ResponseBody List<PreOrder> getAllPreOrdersOfCurrentCustomer() {

        User user = (User) request.getSession().getAttribute("user");
        Customer customer = customerDAOService.getCustomer(user.getUserId());

        if (customer == null) {
            return new ArrayList<PreOrder>();
        }

        return preOrderDAOService.getAllPreOrdersOfCurrentCustomer(customer);
    }

    /**
     * @param customerid
     * @return
     */
    @RequestMapping(value = "/all/customer", params = {"customerid"}, method = RequestMethod.GET)
    @ResponseBody
    public List<org.opensms.app.view.entity.PreOrder> getAllPreOrdersFromCustomer(@RequestParam("customerid") String customerid) {
        List<org.opensms.app.view.entity.PreOrder> preOrders = new ArrayList<org.opensms.app.view.entity.PreOrder>();
        List<PreOrder> preOrderList = preOrderDAOService.getPreOrdersFrom(customerid);
        for(PreOrder preOrder:preOrderList){
            org.opensms.app.view.entity.PreOrder order=new org.opensms.app.view.entity.PreOrder();
            order.updatePreOrder(preOrder);
            preOrders.add(order);
        }
        return preOrders;
    }
}
