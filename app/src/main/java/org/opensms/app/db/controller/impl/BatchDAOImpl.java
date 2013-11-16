package org.opensms.app.db.controller.impl;

import org.opensms.app.db.controller.BatchDAO;
import org.opensms.app.db.entity.Batch;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: dewmal
 * Date: 11/15/13
 * Time: 1:10 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class BatchDAOImpl extends AbstractDAOImpl<Batch,String> implements BatchDAO {


    public BatchDAOImpl() {
        super(Batch.class, String.class);
    }
}
