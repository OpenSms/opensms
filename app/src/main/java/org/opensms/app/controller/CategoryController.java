package org.opensms.app.controller;

import org.opensms.app.db.entity.Category;
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
 * Date: 10/26/13
 * Time: 6:20 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(value = "category")
public class CategoryController {

    @Autowired
    private ItemDAOService itemDAOService;

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public @ResponseBody List<Category> getCategoryList(@RequestParam(value = "hint")String hint) {
        return itemDAOService.getCategoryList(hint);
    }

    @RequestMapping(value = "/allparents",method = RequestMethod.GET,params = {"hint"})
    public @ResponseBody List<Category> getParentCategoryList(@RequestParam(value = "hint")String hint) {
        System.out.println(hint);

        return itemDAOService.getParentCategoryList(hint);
    }

}