package org.opensms.app.db.controller.impl;

import org.opensms.app.db.controller.GsrOrderDAO;
import org.opensms.app.db.entity.GsrOrder;
import org.springframework.stereotype.Repository;

/**
 * Created by dewmal on 1/3/14.
 */
@Repository
public class GsrOrderDAOImpl extends AbstractDAOImpl<GsrOrder,Long> implements GsrOrderDAO {

    public GsrOrderDAOImpl() {
        super(GsrOrder.class,Long.class);
    }
}
