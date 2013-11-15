package org.opensms.app.db.controller.impl;

import org.hibernate.Query;
import org.opensms.app.db.entity.PreOrder;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sadika
 * Date: 11/6/13
 * Time: 9:29 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class PreOrderDAOController extends AbstractDAOImpl<PreOrder, Long>{
    public PreOrderDAOController() {
        super(PreOrder.class, Long.class);
    }

    public List<PreOrder> getPreOrdersAt(String location) {
        location = "%" + location + "%";

        Query query = getCurrentSession().createQuery("SELECT p FROM PreOrder p, UserContactDetail u WHERE " +
                "p.customer = u.userId AND  p.isOpen=true AND u.city LIKE :location " +
                " ORDER BY p.priority DESC, p.preOrderDate ASC");

        query.setString("location", location);

        return query.list();
    }
}
