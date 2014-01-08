package org.opensms.app.db.controller.impl;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.opensms.app.db.controller.GsrOrderDAO;
import org.opensms.app.db.entity.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dewmal on 1/3/14.
 */
@Repository
public class GsrOrderDAOImpl extends AbstractDAOImpl<GsrOrder, Long> implements GsrOrderDAO {

    public GsrOrderDAOImpl() {
        super(GsrOrder.class, Long.class);
    }

    @Override
    public GsrOrder get(Long id) {
        GsrOrder gsrOrder = super.get(id);

        Hibernate.initialize(gsrOrder);
        Customer customer = gsrOrder.getCustomer();
        Hibernate.initialize(customer);
        Hibernate.initialize(customer.getUser());
        for(GsrPayment gsrPayment:gsrOrder.getGsrPaymentList()){
            Hibernate.initialize(gsrPayment);
        }

        for (GsrOrder order : customer.getGsrOrderList()) {
            Hibernate.initialize(order);
        }

        for (PreOrder order : customer.getPreOrderList()) {
            Hibernate.initialize(order);
        }

        for(IisOrderBatchHasGsrOrder order:gsrOrder.getIisOrderBatchHasGsrOrderList()){
            Hibernate.initialize(order);
        }

        return gsrOrder;
    }
    
    @Override
    public List<GsrOrder> getAllGsrOrdersOfCurrentCustomer(Customer customer) {

        Session session = getCurrentSession();
        Query query = session.createQuery("SELECT g FROM GsrOrder g WHERE g.customer=:customer");
        query.setParameter("customer", customer);

        List<GsrOrder> gsrOrders = query.list();

        for (GsrOrder g : gsrOrders) {
            Hibernate.initialize(g.getGsrPaymentList());
            Hibernate.initialize(g.getIisOrderBatchHasGsrOrderList());
            Hibernate.initialize(g.getIisOrderBatchHasGsrOrderList());

            for (IisOrderBatchHasGsrOrder order : g.getIisOrderBatchHasGsrOrderList()) {
                Hibernate.initialize(order.getIisOrderHasBatch().getBatch1().getItem());
            }
        }

        return gsrOrders;
    }
}
