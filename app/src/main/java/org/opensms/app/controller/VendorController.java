package org.opensms.app.controller;

import org.opensms.app.db.entity.Vendor;
import org.opensms.app.db.service.VendorDAOService;
import org.opensms.app.view.model.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 * User: sadika
 * Date: 10/14/13
 * Time: 9:55 AM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(value = "/vendor")
public class VendorController {
    @Autowired
    private VendorDAOService vendorDAOService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody ResponseMessage saveVendor(@RequestBody Vendor vendor) {
        vendorDAOService.save(vendor);

        return new ResponseMessage(ResponseMessage.Type.success, "vendor saveCustomer");
    }
}
