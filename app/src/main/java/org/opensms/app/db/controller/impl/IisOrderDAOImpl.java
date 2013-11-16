package org.opensms.app.db.controller.impl;

import org.opensms.app.db.controller.IisOrderDAO;
import org.opensms.app.db.entity.IisOrder;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: sadika
 * Date: 11/16/13
 * Time: 1:08 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class IisOrderDAOImpl extends AbstractDAOImpl<IisOrder, Long> implements IisOrderDAO {
    public IisOrderDAOImpl() {
        super(IisOrder.class, Long.class);
    }
}
