package org.opensms.app.controller;

import org.opensms.app.db.entity.Unit;
import org.opensms.app.db.service.ItemDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dewmal
 * Date: 10/26/13
 * Time: 6:19 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(value = "/unit")
public class UnitController {

    @Autowired
    private ItemDAOService itemDAOService;

    /**
     *
     * @return
     */
    @RequestMapping(value = "/all")
    public @ResponseBody List<Unit> getUnitList() {
        return itemDAOService.getUnitList("");
    }
}
