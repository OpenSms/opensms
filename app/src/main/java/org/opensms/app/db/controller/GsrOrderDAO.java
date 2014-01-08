package org.opensms.app.db.controller;

import org.opensms.app.db.entity.Customer;
import org.opensms.app.db.entity.GsrOrder;

import java.util.List;

/**
 * Created by dewmal on 1/3/14.
 */
public interface GsrOrderDAO extends AbstractDAO<GsrOrder,Long> {
    List<GsrOrder> getAllGsrOrdersOfCurrentCustomer(Customer customer);
}
