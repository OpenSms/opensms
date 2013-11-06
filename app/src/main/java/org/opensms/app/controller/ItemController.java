package org.opensms.app.controller;

import org.opensms.app.db.entity.Item;
import org.opensms.app.db.service.ItemDAOService;
import org.opensms.app.view.model.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveItem(@RequestBody Item item) {
        itemDAOService.saveItem(item);
        return new ResponseMessage(ResponseMessage.Type.success, "success");
    }
}
