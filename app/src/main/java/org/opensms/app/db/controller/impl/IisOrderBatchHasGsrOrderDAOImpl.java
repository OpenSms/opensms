package org.opensms.app.db.controller.impl;

import org.opensms.app.db.controller.IisOrderBatchHasGsrOrderDAO;
import org.opensms.app.db.entity.IisOrderBatchHasGsrOrder;
import org.opensms.app.db.entity.IisOrderBatchHasGsrOrderPK;
import org.springframework.stereotype.Repository;

/**
 * Created by dewmal on 1/3/14.
 */
@Repository
public class IisOrderBatchHasGsrOrderDAOImpl extends AbstractDAOImpl<IisOrderBatchHasGsrOrder, IisOrderBatchHasGsrOrderPK> implements IisOrderBatchHasGsrOrderDAO {


    public IisOrderBatchHasGsrOrderDAOImpl() {
        super(IisOrderBatchHasGsrOrder.class, IisOrderBatchHasGsrOrderPK.class);
    }
}
