package org.opensms.app.controller;

import org.opensms.app.db.entity.Batch;
import org.opensms.app.db.service.BatchDAOService;
import org.opensms.app.db.service.ItemDAOService;
import org.opensms.app.view.model.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dewmal
 * Date: 11/15/13
 * Time: 12:23 PM
 * To change this template use File | Settings | File Templates.
 */
@RequestMapping(value = "batch")
@Controller
public class BatchController {

    @Autowired
    private BatchDAOService batchDAOService;

    @RequestMapping(value = "/maxitemcount",params = {"item"},method = RequestMethod.GET)
    @ResponseBody
    public double getMaxItemCount(@RequestParam("item")String item){

        BigDecimal bigDecimal=batchDAOService.getMaxItemCount(item);
        if(bigDecimal==null){
            bigDecimal=new BigDecimal(0);
        }
        return bigDecimal.doubleValue();
    }

    @RequestMapping(value = "/all", params = {"itemid"}, method = RequestMethod.GET)
    public @ResponseBody List<Batch> getAll(@RequestParam("itemid") String itemId) {
        return batchDAOService.getAll(itemId);
    }
}
