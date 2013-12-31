package org.opensms.app.controller;

import org.opensms.app.db.entity.UserContactDetail;
import org.opensms.app.db.service.ContactDetailsDAOService;
import org.opensms.app.view.model.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public @ResponseBody ResponseMessage saveContactDetails(@RequestBody UserContactDetail contactDetail) {
        contactDetailsDAOService.saveContactDetails(contactDetail);

        return new ResponseMessage(ResponseMessage.Type.success, "Save contact details");
    }

    /**
     * Update contact details
     * @param contactDetail
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public @ResponseBody ResponseMessage updateContactDetails(@RequestBody UserContactDetail contactDetail) {
        contactDetailsDAOService.updateContactDetails(contactDetail);

        return new ResponseMessage(ResponseMessage.Type.success, "updateContactDetails()");
    }

    @RequestMapping(method = RequestMethod.GET, params = {"userId"})
    public @ResponseBody UserContactDetail getContactDetails(@RequestParam("userId") Integer userId) {
        return contactDetailsDAOService.getContactDetails(userId);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody List<UserContactDetail> getAll() {
        return contactDetailsDAOService.getAll();
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public @ResponseBody List<UserContactDetail> search(@RequestParam("query") String query) {
        return contactDetailsDAOService.search(query);
    }
}
