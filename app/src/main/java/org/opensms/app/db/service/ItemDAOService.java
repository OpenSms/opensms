package org.opensms.app.db.service;

import org.opensms.app.db.controller.impl.CategoryDAOController;
import org.opensms.app.db.controller.impl.UnitDAOController;
import org.opensms.app.db.entity.Category;
import org.opensms.app.db.entity.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dewmal
 * Date: 10/26/13
 * Time: 6:28 PM
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class ItemDAOService {

    @Autowired
    private UnitDAOController unitDAOController;
    @Autowired
    private CategoryDAOController categoryDAOController;

    public List<Category> getCategoryList(String hint) {
        return categoryDAOController.getAll(hint);
    }

    public List<Unit> getUnitList(String s) {
        return unitDAOController.getAll();
    }

    public List<Category> getParentCategoryList(String hint) {
        return categoryDAOController.getAllParents(hint);
    }
}
