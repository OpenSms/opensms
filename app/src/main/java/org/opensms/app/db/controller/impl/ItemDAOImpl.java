package org.opensms.app.db.controller.impl;

import org.hibernate.Query;
import org.opensms.app.db.controller.ItemDAO;
import org.opensms.app.db.entity.Item;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dewmal
 * Date: 11/6/13
 * Time: 10:10 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class ItemDAOImpl extends AbstractDAOImpl<Item, String> implements ItemDAO {
    public ItemDAOImpl() {
        super(Item.class, String.class);
    }

    @Override
    public List<Item> getAll(String hint) {
        Query query = getCurrentSession().createQuery("select i from Item  i where i.name like :hint");
        query.setString("hint", "%"+hint+"%");
        return query.list();
    }
}
