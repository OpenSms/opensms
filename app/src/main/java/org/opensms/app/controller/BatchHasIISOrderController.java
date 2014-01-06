package org.opensms.app.controller;

import org.opensms.app.db.entity.IisOrderHasBatch;
import org.opensms.app.db.service.IisOrderDAOService;
import org.opensms.app.view.entity.IISOrderHasBatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by dewmal on 1/5/14.
 */
@RequestMapping(value = "/batchhassiisorder")
@Controller
public class BatchHasIISOrderController {


    @Autowired
    private IisOrderDAOService iisOrderDAOService;


    /**
     * Get all items
     *
     * @return List<Item>
     */
    @RequestMapping(value = "/getsalesbatch", method = RequestMethod.GET, params = {"itemid", "salesperson"})
    public
    @ResponseBody
    List<IisOrderHasBatch> getItem(@RequestParam("itemid") String itemid, @RequestParam("salesperson") String salesperson) {
        List<IisOrderHasBatch> orderHasBatch = iisOrderDAOService.getIISOrderBatch(itemid, salesperson);
        System.out.println(orderHasBatch);
        return orderHasBatch;
    }


}
