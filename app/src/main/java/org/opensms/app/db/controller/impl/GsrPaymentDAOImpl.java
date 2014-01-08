package org.opensms.app.db.controller.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.opensms.app.db.controller.GsrPaymentDAO;
import org.opensms.app.db.entity.GsrPayment;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dewmal on 1/8/14.
 */
@Repository
public class GsrPaymentDAOImpl extends AbstractDAOImpl<GsrPayment, Long> implements GsrPaymentDAO {
    public GsrPaymentDAOImpl() {
        super(GsrPayment.class, Long.class);
    }

    @Override
    public List<GsrPayment> getAllById(String gsrorderid) {
        Session session = getCurrentSession();
        Query query = session.createQuery("SELECT gp FROM GsrPayment gp WHERE gp.gsrOrder.gsrOrderId=:gsrOrderId");
        query.setLong("gsrOrderId", Long.parseLong(gsrorderid));
        return query.list();
    }
}
