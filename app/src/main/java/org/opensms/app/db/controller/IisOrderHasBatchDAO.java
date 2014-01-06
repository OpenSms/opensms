package org.opensms.app.db.controller;

import org.opensms.app.db.entity.IisOrder;
import org.opensms.app.db.entity.IisOrderHasBatch;
import org.opensms.app.db.entity.IisOrderHasBatchPK;
import org.opensms.app.view.entity.IISOrderHasBatch;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sadika
 * Date: 11/16/13
 * Time: 5:51 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IisOrderHasBatchDAO extends AbstractDAO<IisOrderHasBatch, IisOrderHasBatchPK> {


    List<IisOrderHasBatch> getAllByIisOrder(String iisorder_id);

    List<IisOrderHasBatch> getBatchByItemIdAndIISOrder(String itemid, Long iisOrderId);
}
