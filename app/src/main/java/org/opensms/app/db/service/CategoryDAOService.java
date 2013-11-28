package org.opensms.app.db.service;

import org.opensms.app.db.controller.impl.CategoryDAOController;
import org.opensms.app.db.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA.
 * User: sadika
 * Date: 11/24/13
 * Time: 5:58 PM
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class CategoryDAOService {

    @Autowired
    private CategoryDAOController categoryDAOController;

    public void saveCategory(Category category) {

        categoryDAOController.save(category);
    }
}
