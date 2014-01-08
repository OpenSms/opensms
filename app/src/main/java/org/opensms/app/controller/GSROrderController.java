package org.opensms.app.controller;

import org.opensms.app.db.entity.Batch;
import org.opensms.app.db.entity.Customer;
import org.opensms.app.db.entity.GsrOrder;
import org.opensms.app.db.entity.User;
import org.opensms.app.db.service.CustomerDAOService;
import org.opensms.app.db.service.GsrOrderDAOService;
import org.opensms.app.db.entity.*;
import org.opensms.app.db.service.*;
import org.opensms.app.view.entity.GsrOrderModel;
import org.opensms.app.view.model.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;
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
    private ItemDAOService itemDAOService;

    @Autowired
    private PreOrderDAOService preOrderDAOService;


    @Autowired
    private IisOrderDAOService iisOrderDAOService;


    @Autowired
    private UserDAOService userDAOService;

    @Autowired
    private HttpServletRequest context;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage createGsrOrder(@RequestBody GsrOrderModel gsrOrderModel) {
        System.out.println(gsrOrderModel);

        User user = (User) context.getSession().getAttribute("user");
        gsrOrderModel.setSales_person(user.getUserId()+"");
        Long id = gsrOrderDAOService.save(gsrOrderModel);
        return new ResponseMessage(ResponseMessage.Type.success, "ok");
    }

    @RequestMapping(value = "/get",params = {"orderId"},method = RequestMethod.GET)
    @ResponseBody
    public GsrOrder getOrder(@RequestParam("orderId") long orderId){
        return gsrOrderDAOService.getOrder(orderId);
    }


    @RequestMapping(value = "/getpreorders", method = RequestMethod.GET, params = {"userid"})
    @ResponseBody
    public List<PreOrder> getPreOrders(@RequestParam("userid") String userid) {
        return preOrderDAOService.getPreOrdersFrom(userid);
    }


    @RequestMapping(value = "/getpreorderitesm", method = RequestMethod.GET, params = {"preorderid"})
    @ResponseBody
    public List<PreOrderHasItem> getPreOrderHasItems(@RequestParam("preorderid") String preorderid) {
        return preOrderDAOService.getPreOrderHasItemsOf(Long.parseLong(preorderid));
    }


    @RequestMapping(value = "/getpreorderitesm", method = RequestMethod.GET, params = {"salesEmp", "itemid"})
    @ResponseBody
    public List<IisOrderHasBatch> getIisOrderHasBatches(@RequestParam("salesEmp") String salesEmp,
                                                        @RequestParam("itemid") String itemid) {
        User user = userDAOService.getUser(salesEmp);
        salesEmp=user.getId()+"";
        return iisOrderDAOService.getIISOrderBatch(itemid, salesEmp);
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
