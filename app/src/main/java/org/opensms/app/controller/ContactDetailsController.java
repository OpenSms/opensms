package org.opensms.app.controller;

import org.opensms.app.db.entity.UserContactDetail;
import org.opensms.app.db.service.ContactDetailsDAOService;
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
 * Date: 10/9/13
 * Time: 5:53 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/contactdetails")
public class ContactDetailsController {
    @Autowired
    private ContactDetailsDAOService contactDetailsDAOService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody
    ResponseMessage saveContactDetails(@RequestBody UserContactDetail contactDetail) {
        contactDetailsDAOService.saveContactDetails(contactDetail);

        return new ResponseMessage(ResponseMessage.Type.success, "Save contact details");
    }
}
