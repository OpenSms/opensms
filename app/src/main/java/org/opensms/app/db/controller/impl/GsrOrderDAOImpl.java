package org.opensms.app.db.controller.impl;

import org.hibernate.Hibernate;
import org.opensms.app.db.controller.GsrOrderDAO;
import org.opensms.app.db.entity.Customer;
import org.opensms.app.db.entity.GsrOrder;
import org.opensms.app.db.entity.IisOrderBatchHasGsrOrder;
import org.opensms.app.db.entity.PreOrder;
import org.springframework.stereotype.Repository;

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
}
