package org.opensms.app.db.controller.impl;

import org.hibernate.Query;
import org.opensms.app.db.controller.BatchDAO;
import org.opensms.app.db.entity.Batch;
import org.opensms.app.db.entity.Item;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dewmal
 * Date: 11/15/13
 * Time: 1:10 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class BatchDAOImpl extends AbstractDAOImpl<Batch,String> implements BatchDAO {


    public BatchDAOImpl() {
        super(Batch.class, String.class);
    }

    /**
     * Get all batches of given item with remaining quantity is greater than zero
     * @param item
     * @return
     */
    @Override
    public List<Batch> getBatchesWithItemId(Item item) {

        Query query = getCurrentSession().createQuery("SELECT b FROM Batch b, GrnOrder g WHERE b.remainingQuantity > 0 " +
                "AND b.grnOrder.grnOrderId = g.grnOrderId " +
                "AND b.item = :item ORDER BY g.receiveDate ASC");

        query.setParameter("item", item);

        return query.list();
    }
}
