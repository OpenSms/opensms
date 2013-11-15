package org.opensms.app.controller;

import org.opensms.app.db.entity.Item;
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
 * Time: 6:18 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(value = "item")
public class ItemController {

    @Autowired
    private ItemDAOService itemDAOService;

    /**
     * Save new Item
     *
     * @param item
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveItem(@RequestBody Item item) {
        itemDAOService.saveItem(item);
        return new ResponseMessage(ResponseMessage.Type.success, "success");


    }

    /**
     * get all items related with hint
     *
     * @param hint
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET, params = {"hint"})
    @ResponseBody
    public List<Item> getAllItemsList(@RequestParam String hint) {
        return itemDAOService.getAllItemList(hint);
    }
}
