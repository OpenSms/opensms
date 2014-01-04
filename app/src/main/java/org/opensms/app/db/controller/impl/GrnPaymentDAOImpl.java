package org.opensms.app.db.controller.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.opensms.app.db.controller.GrnPaymentDAO;
import org.opensms.app.db.entity.GrnPayment;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sadika on 1/4/14.
 */
@Repository
public class GrnPaymentDAOImpl extends AbstractDAOImpl<GrnPayment, Long> implements GrnPaymentDAO {
    public GrnPaymentDAOImpl() {
        super(GrnPayment.class, Long.class);
    }

    public List<GrnPayment> getAllPayments(Long grnOrderId) {

        Session currentSession = getCurrentSession();

        Query query = currentSession.createQuery("select g from GrnPayment g where g.grnOrder.grnOrderId = :grnOrderId");
        query.setLong("grnOrderId", grnOrderId);

        return query.list();
    }
}
