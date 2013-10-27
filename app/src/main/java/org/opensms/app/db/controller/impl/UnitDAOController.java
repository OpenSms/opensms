package org.opensms.app.db.controller.impl;

import org.opensms.app.db.entity.Unit;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: dewmal
 * Date: 10/26/13
 * Time: 6:28 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class UnitDAOController extends AbstractDAOImpl<Unit,Integer> {
    public UnitDAOController() {
        super(Unit.class, Integer.class);
    }
}
