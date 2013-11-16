package org.opensms.app.controller;

import org.opensms.app.db.service.IisOrderDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: sadika
 * Date: 11/16/13
 * Time: 1:02 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(value = "/iisorder")
public class IisOrderController {
    @Autowired
    private IisOrderDAOService iisOrderDAOService;
}
