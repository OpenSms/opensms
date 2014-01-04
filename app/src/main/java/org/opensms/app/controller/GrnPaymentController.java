package org.opensms.app.controller;

import org.opensms.app.db.controller.impl.EmployeeDAOController;
import org.opensms.app.db.entity.Employee;
import org.opensms.app.db.entity.GrnPayment;
import org.opensms.app.db.entity.User;
import org.opensms.app.db.service.EmployeeDAOService;
import org.opensms.app.db.service.GrnPaymentDAOService;
import org.opensms.app.view.model.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.List;

/**
 * Created by sadika on 1/3/14.
 */
@Controller
@RequestMapping(value = "/grnpayment")
public class GrnPaymentController {

    @Autowired
    private GrnPaymentDAOService grnPaymentDAOService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private EmployeeDAOService employeeDAOService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody public ResponseMessage save(@RequestBody GrnPayment grnPayment) {

        // Assuming current user is an employee
        User user = (User) request.getSession().getAttribute("user");
        Employee employee = employeeDAOService.getEmployee(user.getId());

        grnPayment.setCashierEmployee(employee);
        grnPayment.setPaymentDate(Calendar.getInstance().getTime());

        grnPaymentDAOService.save(grnPayment);

        return new ResponseMessage(ResponseMessage.Type.success, "GrnPaymentController.saved()");
    }

    @RequestMapping(method = RequestMethod.GET, params = {"grnorderid"})
    @ResponseBody public List<GrnPayment> getAllPayments(@RequestParam ("grnorderid") Long grnOrderId) {
        return grnPaymentDAOService.getAllPayments(grnOrderId);
    }
}
