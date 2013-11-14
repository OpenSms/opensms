package org.opensms.app.db.controller.impl;

import org.opensms.app.db.entity.PreOrder;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: sadika
 * Date: 11/6/13
 * Time: 9:29 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class PreOrderDAOController extends AbstractDAOImpl<PreOrder, Long>{
    public PreOrderDAOController() {
        super(PreOrder.class, Long.class);
    }
}
