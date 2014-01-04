package org.opensms.app.db.service;

import org.opensms.app.db.controller.GrnPaymentDAO;
import org.opensms.app.db.entity.GrnPayment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by sadika on 1/4/14.
 */
@Service
@Transactional
public class GrnPaymentDAOService {

    @Autowired
    private GrnPaymentDAO grnPaymentDAO;

    public List<GrnPayment> getAllPayments(Long grnOrderId) {
        return grnPaymentDAO.getAllPayments(grnOrderId);
    }

    public void save(GrnPayment grnPayment) {
        grnPaymentDAO.save(grnPayment);
    }
}
