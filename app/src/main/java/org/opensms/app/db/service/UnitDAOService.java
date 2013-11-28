package org.opensms.app.db.service;

import org.opensms.app.db.controller.impl.UnitDAOController;
import org.opensms.app.db.entity.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA.
 * User: sadika
 * Date: 11/24/13
 * Time: 4:00 PM
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class UnitDAOService {

    @Autowired
    UnitDAOController unitDAOController;

    public void saveUnit(Unit unit) {
        unitDAOController.save(unit);
    }
}
