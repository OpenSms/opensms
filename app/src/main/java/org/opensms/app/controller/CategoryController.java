package org.opensms.app.controller;

import org.opensms.app.db.entity.Category;
import org.opensms.app.db.service.ItemDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping(value = "/getall")
    public List<Category> getCategoryList() {
        return itemDAOService.getCategoryList("");
    }

}