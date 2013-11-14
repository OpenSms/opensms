package org.opensms.app.controller;

import org.opensms.app.db.entity.Profit;
import org.opensms.app.db.service.ItemDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    private ItemDAOService itemDAOService;


    @RequestMapping(value = "/all",method = RequestMethod.GET,params = {"type"})
    public @ResponseBody List<Profit> getAllProfits(@RequestParam(value = "type")String type){
        return itemDAOService.getAllProfits(type);
    }

}
