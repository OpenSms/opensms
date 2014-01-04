package org.opensms.app.db.controller.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.opensms.app.db.controller.IisOrderHasBatchDAO;
import org.opensms.app.db.entity.IisOrder;
import org.opensms.app.db.entity.IisOrderHasBatch;
import org.opensms.app.db.entity.IisOrderHasBatchPK;
import org.springframework.stereotype.Repository;

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
}
