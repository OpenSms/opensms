package org.opensms.app.db.service;

import org.opensms.app.db.controller.impl.CategoryDAOController;
import org.opensms.app.db.controller.impl.ProfitDAOController;
import org.opensms.app.db.controller.impl.UnitDAOController;
import org.opensms.app.db.entity.Category;
import org.opensms.app.db.entity.Profit;
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

    @Autowired
    private ProfitDAOController profitDAOController;


    /**
     * Get All Units As List
     *
     * @param s
     * @return
     */
    public List<Unit> getUnitList(String s) {
        return unitDAOController.getAll();
    }


    /**
     * Get All Child Categories
     *
     * @param hint
     * @return
     */
    public List<Category> getCategoryList(String hint) {
        return categoryDAOController.getAll(hint);
    }


    /**
     * Get Parent Categories by Category DAO Controller
     *
     * @param hint
     * @return
     */
    public List<Category> getParentCategoryList(String hint) {
        return categoryDAOController.getAllParents(hint);
    }

    /**
     * Get Profits
     * @param type
     * @return
     */
    public List<Profit> getAllProfits(String type){
        return profitDAOController.getAll(type );
    }
}
