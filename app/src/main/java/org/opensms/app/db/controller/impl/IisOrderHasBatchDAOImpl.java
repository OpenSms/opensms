package org.opensms.app.db.controller.impl;

import org.opensms.app.db.controller.IisOrderHasBatchDAO;
import org.opensms.app.db.entity.IisOrderHasBatch;
import org.opensms.app.db.entity.IisOrderHasBatchPK;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: sadika
 * Date: 11/16/13
 * Time: 5:53 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class IisOrderHasBatchDAOImpl extends AbstractDAOImpl<IisOrderHasBatch, IisOrderHasBatchPK> implements IisOrderHasBatchDAO {
    public IisOrderHasBatchDAOImpl() {
        super(IisOrderHasBatch.class, IisOrderHasBatchPK.class);
    }
}
