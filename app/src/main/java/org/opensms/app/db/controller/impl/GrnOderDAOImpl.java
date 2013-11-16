package org.opensms.app.db.controller.impl;

import org.opensms.app.db.controller.AbstractDAO;
import org.opensms.app.db.controller.GrnOrderDAO;
import org.opensms.app.db.entity.GrnOrder;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: dewmal
 * Date: 11/15/13
 * Time: 12:38 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class GrnOderDAOImpl extends AbstractDAOImpl<GrnOrder,Long> implements GrnOrderDAO {
    public GrnOderDAOImpl() {
        super(GrnOrder.class, Long.class);
    }
}
