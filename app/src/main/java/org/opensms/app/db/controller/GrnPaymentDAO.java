package org.opensms.app.db.controller;

import org.opensms.app.db.entity.GrnPayment;

import java.util.List;

/**
 * Created by sadika on 1/4/14.
 */
public interface GrnPaymentDAO extends AbstractDAO<GrnPayment, Long>  {
    List<GrnPayment> getAllPayments(Long grnOrderId);
}
