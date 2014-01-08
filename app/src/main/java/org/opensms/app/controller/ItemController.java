package org.opensms.app.controller;

import org.opensms.app.db.entity.Item;
import org.opensms.app.db.entity.PreOrderHasItem;
import org.opensms.app.db.service.ItemDAOService;
import org.opensms.app.db.service.PreOrderDAOService;
import org.opensms.app.view.entity.PreOrderHasItems;
import org.opensms.app.view.entity.PreOrderList;
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
@RequestMapping(value = "/item")
public class ItemController {

    @Autowired
    private ItemDAOService itemDAOService;

    @Autowired
    private PreOrderDAOService preOrderDAOService;

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

    /**
     * Get all items
     *
     * @return List<Item>
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Item> getAllItems() {
        return itemDAOService.getAllItems();
    }


    /**
     * Get all items
     *
     * @return List<Item>
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET, params = {"itemid"})
    public
    @ResponseBody
    Item getItem(@RequestParam("itemid") String itemid) {
        return itemDAOService.getItem(itemid);
    }


    @RequestMapping(value = "/bypreorder", method = RequestMethod.GET)
    public  @ResponseBody PreOrderHasItems getByPreOrder(@RequestBody PreOrderList preorderid) {
        PreOrderHasItems preOrderHasItems1 = new PreOrderHasItems();
        List<PreOrderHasItem> itemByPreOrderId = preOrderDAOService.getItemByPreOrderId(preorderid.getPreString());
        for (PreOrderHasItem orderHasItem:itemByPreOrderId){

            preOrderHasItems1.insertPreOrderHasItem(orderHasItem);
        }

        System.out.println(preOrderHasItems1);
        return preOrderHasItems1;
    }


}
