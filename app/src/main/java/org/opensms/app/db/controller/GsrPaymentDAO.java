package org.opensms.app.db.controller;

import org.opensms.app.db.entity.GsrPayment;

import java.util.List;

/**
 * Created by dewmal on 1/8/14.
 */
public interface GsrPaymentDAO extends AbstractDAO<GsrPayment,Long>{
    List<GsrPayment> getAllById(String gsrorderid);
}
