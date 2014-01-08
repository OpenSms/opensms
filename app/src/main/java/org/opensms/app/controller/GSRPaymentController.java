package org.opensms.app.controller;

import org.opensms.app.db.entity.*;
import org.opensms.app.db.service.GsrOrderDAOService;
import org.opensms.app.view.entity.GsrPaymentModel;
import org.opensms.app.view.model.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

/**
 * Created by dewmal on 1/8/14.
 */
@Controller
@RequestMapping(value = "/gsrpayment")
public class GSRPaymentController {

    @Autowired
    private GsrOrderDAOService gsrOrderDAOService;

    @Autowired
    private HttpServletRequest request;


    @RequestMapping(value = "/gsrorder", params = {"gsrorderid"}, method = RequestMethod.GET)
    @ResponseBody
    public List<GsrPayment> getALlPayemnts(@RequestParam("gsrorderid") String gsrorderid) {
        return gsrOrderDAOService.getGsrPayments(gsrorderid);
    }


    @RequestMapping(value = "/saved", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage savePaymentMethod(@RequestBody GsrPaymentModel gsrPaymentModel) {

        GsrPayment gsrPayment=new GsrPayment();
        gsrPayment.setAmount(new BigDecimal(gsrPaymentModel.getAmount()));
        PaymentMethod method=new PaymentMethod();
        method.setPaymentMethodId(Integer.parseInt(gsrPaymentModel.getPaymentmethod()));
        gsrPayment.setPaymentMethod(method);
        GsrOrder gsrOrder=new GsrOrder(Long.parseLong(gsrPaymentModel.getGsrOrder()));
        gsrPayment.setGsrOrder(gsrOrder);

        User user= (User) request.getSession().getAttribute("user");
        Employee employee=new Employee();
        employee.setUser(user);
        employee.setUserId(user.getUserId());
        gsrPayment.setCashierEmployee(employee);
        gsrPayment.setPaymentDate(Calendar.getInstance().getTime());
        System.out.println("asdasdasdasd+" + gsrPayment.getsAmount());
        gsrOrderDAOService.saveGsrOrderPayment(gsrPayment);
        return new ResponseMessage(ResponseMessage.Type.success, gsrPayment + "");
    }
}
