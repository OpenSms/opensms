package org.opensms.app.db.service;

import org.opensms.app.db.controller.PaymentMethodDAO;
import org.opensms.app.db.entity.PaymentMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by sadika on 1/3/14.
 */
@Service
@Transactional
public class PaymentMethodDAOService {

    @Autowired
    private PaymentMethodDAO paymentMethodDAO;

    public List<PaymentMethod> getAllPaymentMethods() {
        return paymentMethodDAO.getAll();
    }
}
