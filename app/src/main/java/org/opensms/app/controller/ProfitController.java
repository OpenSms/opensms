package org.opensms.app.controller;

import org.opensms.app.db.entity.Profit;
import org.opensms.app.db.service.ItemDAOService;
import org.opensms.app.db.service.ProfitDAOService;
import org.opensms.app.view.model.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dewmal
 * Date: 10/28/13
 * Time: 5:40 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(value = "profit")
public class ProfitController {

    @Autowired
    private ProfitDAOService profitDAOService;

    @Autowired
    private ItemDAOService itemDAOService;


    @RequestMapping(value = "/all",method = RequestMethod.GET,params = {"type"})
    public @ResponseBody List<Profit> getAllProfits(@RequestParam(value = "type")String type){
        return itemDAOService.getAllProfits(type);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody ResponseMessage saveProfit(@RequestBody Profit profit) {
        profitDAOService.saveProfit(profit);
        return new ResponseMessage(ResponseMessage.Type.success, "profit saved.");
    }
}
