package org.opensms.app.db.controller.impl;

import org.hibernate.Query;
import org.opensms.app.db.entity.Profit;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dewmal
 * Date: 10/28/13
 * Time: 5:33 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class ProfitDAOController extends AbstractDAOImpl<Profit, Integer> {

    public ProfitDAOController() {
        super(Profit.class, Integer.class);
    }

    /**
     * Get All by given profit type
     *
     * @param type
     * @return
     */
    public List<Profit> getAll(String type) {
        Query query = getCurrentSession().getNamedQuery("Profit.findByType");
        query.setString("type", type);
        return query.list();

    }
}
