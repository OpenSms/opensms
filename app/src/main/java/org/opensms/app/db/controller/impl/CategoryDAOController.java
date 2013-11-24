package org.opensms.app.db.controller.impl;

import org.hibernate.Query;
import org.opensms.app.db.entity.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dewmal
 * Date: 10/26/13
 * Time: 6:30 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class CategoryDAOController extends AbstractDAOImpl<Category, Integer> {
    public CategoryDAOController() {
        super(Category.class, Integer.class);
    }



    public List<Category> getAll(String hint) {
        Query query = getCurrentSession().createQuery("SELECT c FROM Category c WHERE c.category LIKE :hint ");
        query.setString("hint", "%" + hint + "%");

        return query.list();
    }

    public List<Category> getAllParents(String hint) {
        Query query = getCurrentSession().createQuery("SELECT c FROM Category c WHERE c.parentCategory is NULL AND c.category LIKE :hint");
        query.setString("hint", "%" + hint + "%");
        return query.list();
    }
}
