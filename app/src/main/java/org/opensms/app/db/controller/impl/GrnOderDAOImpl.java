package org.opensms.app.db.controller.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.opensms.app.db.controller.AbstractDAO;
import org.opensms.app.db.controller.GrnOrderDAO;
import org.opensms.app.db.entity.GrnOrder;
import org.opensms.app.db.entity.Vendor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dewmal
 * Date: 11/15/13
 * Time: 12:38 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class GrnOderDAOImpl extends AbstractDAOImpl<GrnOrder,Long> implements GrnOrderDAO {
    public GrnOderDAOImpl() {
        super(GrnOrder.class, Long.class);
    }

    @Override
    public List<GrnOrder> getAllGrnOrdersOfCurrentVendor(Vendor vendor) {

        Session session = getCurrentSession();
        Query query = session.createQuery("SELECT g FROM GrnOrder g WHERE g.vendor=:vendor");
        query.setParameter("vendor", vendor);

        List<GrnOrder> list = null;
        try {
            list = query.list();
//            for (GrnOrder grnOrder:list){
//                Hibernate.initialize(grnOrder);
//                for(GrnPayment grnPayment:grnOrder.getGrnPaymentList()){
//                    Hibernate.initialize(grnPayment);
//                }
//
//                for(Batch batch:grnOrder.getBatchList()){
//                    Hibernate.initialize(batch);
//                }
//
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
