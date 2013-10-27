package org.opensms.app.db.controller.impl;

import org.opensms.app.db.entity.Category;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: dewmal
 * Date: 10/26/13
 * Time: 6:30 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class CategoryDAOController extends AbstractDAOImpl<Category,Integer> {
    public CategoryDAOController() {
        super(Category.class, Integer.class);
    }
}
