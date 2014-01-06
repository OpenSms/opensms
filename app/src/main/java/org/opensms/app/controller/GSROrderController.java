package org.opensms.app.controller;

import org.opensms.app.db.entity.IisOrder;
import org.opensms.app.db.entity.IisOrderHasBatch;
import org.opensms.app.db.entity.PreOrder;
import org.opensms.app.db.entity.PreOrderHasItem;
import org.opensms.app.db.service.GsrOrderDAOService;
import org.opensms.app.db.service.IisOrderDAOService;
import org.opensms.app.db.service.ItemDAOService;
import org.opensms.app.db.service.PreOrderDAOService;
import org.opensms.app.view.entity.GsrOrderModel;
import org.opensms.app.view.entity.IISOrderHasBatch;
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


    @RequestMapping(value = "/save", method = RequestMethod.PUT)
    public ResponseMessage createGsrOrder(@RequestBody GsrOrderModel gsrOrderModel) {
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



    @RequestMapping(value = "/getpreorderitesm", method = RequestMethod.GET, params = {"salesEmp"})
    @ResponseBody
    public List<IisOrderHasBatch> getIisOrderHasBatches(@RequestParam("salesEmp") String salesEmp) {
        IisOrder openOrder = iisOrderDAOService.getOpenOrder(salesEmp);
        return iisOrderDAOService.getBatchList(openOrder.getIisOrderId()+"");
    }


}
