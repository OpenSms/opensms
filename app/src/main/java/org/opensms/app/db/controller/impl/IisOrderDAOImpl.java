package org.opensms.app.db.controller.impl;

import org.apache.log4j.Logger;
import org.hibernate.*;
import org.opensms.app.db.controller.IisOrderDAO;
import org.opensms.app.db.entity.IisOrder;
import org.opensms.app.db.entity.PreOrder;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sadika
 * Date: 11/16/13
 * Time: 1:08 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class IisOrderDAOImpl extends AbstractDAOImpl<IisOrder, Long> implements IisOrderDAO {

    private static final Logger LOGGER=Logger.getLogger(IisOrderDAOImpl.class);

    public IisOrderDAOImpl() {
        super(IisOrder.class, Long.class);
    }


    @Override
    public List<IisOrder> getAll(Integer empid) {
        Query query = getCurrentSession().createQuery("SELECT i FROM IisOrder i WHERE i.salesEmployee.userId = :empid");
        query.setInteger("empid", empid);
        return query.list();
    }

    @Override
    public IisOrder getOpenOrder(String sales_person) {
        Session session = getCurrentSession();

        session.setFlushMode(FlushMode.NEVER);
        Query query = session.createQuery("SELECT" +
                " ii FROM IisOrder ii WHERE ii.salesEmployee.id=:slaes_person_id AND" +
                " ii.returnCheckEmployee IS NULL ORDER BY ii.issOrderDateTime ASC" +
                "");
        query.setInteger("slaes_person_id", Integer.parseInt(sales_person));
        IisOrder iisOrder = null;
        try {
            iisOrder = (IisOrder) query.list().get(0);

            for(PreOrder preOrder:iisOrder.getPreOrderList()){
                LOGGER.info(preOrder);
                Hibernate.initialize(preOrder);
            }

            Hibernate.initialize(iisOrder.getSalesEmployee());
            Hibernate.initialize(iisOrder.getItemIssuerEmployee());
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return iisOrder;


    }


   
}
