package org.opensms.app.controller;

import org.opensms.app.db.entity.PaymentMethod;
import org.opensms.app.db.service.PaymentMethodDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by sadika on 1/3/14.
 */
@Controller
@RequestMapping(value = "paymentmethod")
public class PaymentMethodController {

    @Autowired
    PaymentMethodDAOService paymentMethodDAOService;

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public @ResponseBody List<PaymentMethod> getAllPaymentMethods() {
        return paymentMethodDAOService.getAllPaymentMethods();
    }
}
