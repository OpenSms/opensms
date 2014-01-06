package org.opensms.app.controller;

import org.opensms.app.db.entity.IisOrderHasBatch;
import org.opensms.app.db.entity.PreOrder;
import org.opensms.app.db.entity.PreOrderHasItem;
import org.opensms.app.db.entity.User;
import org.opensms.app.db.service.*;
import org.opensms.app.view.entity.GsrOrderModel;
import org.opensms.app.view.model.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    private ItemDAOService itemDAOService;

    @Autowired
    private PreOrderDAOService preOrderDAOService;


    @Autowired
    private IisOrderDAOService iisOrderDAOService;


    private UserDAOService userDAOService;


    @RequestMapping(value = "/save", method = RequestMethod.PUT)
    public ResponseMessage createGsrOrder(@RequestBody GsrOrderModel gsrOrderModel) {

        User user = userDAOService.getUser(gsrOrderModel.getSales_person());
        gsrOrderModel.setSales_person(user.getUserId() + "");

        Long id = gsrOrderDAOService.save(gsrOrderModel);
        return new ResponseMessage(ResponseMessage.Type.success, "ok");
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


}
