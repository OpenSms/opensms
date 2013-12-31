package org.opensms.app.controller;

import org.opensms.app.db.entity.Category;
import org.opensms.app.db.service.CategoryDAOService;
import org.opensms.app.db.service.ItemDAOService;
import org.opensms.app.view.model.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    private CategoryDAOService categoryDAOService;

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

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody ResponseMessage saveCategory(@RequestBody Category category) {
        categoryDAOService.saveCategory(category);
        return new ResponseMessage(ResponseMessage.Type.success, "category saved.");
    }
}