package org.opensms.app.db.controller.impl;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.opensms.app.db.controller.IisOrderHasBatchDAO;
import org.opensms.app.db.entity.IisOrder;
import org.opensms.app.db.entity.IisOrderHasBatch;
import org.opensms.app.db.entity.IisOrderHasBatchPK;
import org.opensms.app.view.entity.IISOrderHasBatch;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sadika
 * Date: 11/16/13
 * Time: 5:53 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class IisOrderHasBatchDAOImpl extends AbstractDAOImpl<IisOrderHasBatch, IisOrderHasBatchPK> implements IisOrderHasBatchDAO {
    public IisOrderHasBatchDAOImpl() {
        super(IisOrderHasBatch.class, IisOrderHasBatchPK.class);
    }


    @Override
    public List<IisOrderHasBatch> getAllByIisOrder(String iisorder_id) {

        Session session = getCurrentSession();
        Query query = session.createQuery("SELECT b FROM  IisOrderHasBatch b where " +
                "b.iisOrderHasBatchPK.iisOrder = :iis_order_id");
        query.setLong("iis_order_id",Long.parseLong(iisorder_id));

        return query.list();
    }

    @Override
    public List<IisOrder> getTodaysIisOrders() {

        Session session = getCurrentSession();
        Query query = session.createQuery("SELECT b FROM  IisOrder b ");

//        Date cur = Calendar.getInstance().getTime();
//
//        Date from = new Date(cur.getYear(), cur.getMonth(), cur.getDay(), 00, 0);
//        Date to = new Date(cur.getYear(), cur.getMonth(), cur.getDay(), 23, 59);
//
//        query.setParameter("from", from);
//        query.setParameter("to", to);

        List<IisOrder> iisOrderList = query.list();

        for (IisOrder i : iisOrderList) {
            Hibernate.initialize(i);
            Hibernate.initialize(i.getSalesEmployee());
            Hibernate.initialize(i.getItemIssuerEmployee());
        }

        return query.list();
    }

    @Override
    public List<IisOrder> getTodaysOpenIisOrders() {
        Session session = getCurrentSession();
        Query query = session.createQuery("SELECT b FROM  IisOrder b WHERE b.returnCheckEmployee is NULL");

//        Date cur = Calendar.getInstance().getTime();
//
//        Date from = new Date(cur.getYear(), cur.getMonth(), cur.getDay(), 00, 0);
//        Date to = new Date(cur.getYear(), cur.getMonth(), cur.getDay(), 23, 59);
//
//        query.setParameter("from", from);
//        query.setParameter("to", to);

        List<IisOrder> iisOrderList = query.list();

        for (IisOrder i : iisOrderList) {
            Hibernate.initialize(i);
            Hibernate.initialize(i.getSalesEmployee());
            Hibernate.initialize(i.getItemIssuerEmployee());
        }

        return query.list();
    }

    @Override
    public List<IisOrderHasBatch> getBatchByItemIdAndIISOrder(String itemid, Long iisOrderId) {
        IisOrderHasBatch iisOrderHasBatch= null;
        try {
            System.out.println(itemid);
            System.out.println(iisOrderId);
            Session currentSession = getCurrentSession();

                    Query query=currentSession.createQuery("SELECT i FROM IisOrderHasBatch i WHERE i.iisOrderHasBatchPK.iisOrder = :iisOrder " +
                            "AND i.iisOrderHasBatchPK.batch IN (SELECT b.batchCode FROM Batch b WHERE b.item.itemId=:itemid)");
            query.setLong("iisOrder",iisOrderId);
            query.setString("itemid", itemid);

            return  query.list();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
