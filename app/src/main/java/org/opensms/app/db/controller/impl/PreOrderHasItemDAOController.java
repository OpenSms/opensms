package org.opensms.app.db.controller.impl;

import org.opensms.app.db.entity.PreOrderHasItem;
import org.opensms.app.db.entity.PreOrderHasItemPK;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sadika
 * Date: 11/6/13
 * Time: 9:33 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class PreOrderHasItemDAOController extends AbstractDAOImpl<PreOrderHasItem, PreOrderHasItemPK>{
    public PreOrderHasItemDAOController() {
        super(PreOrderHasItem.class, PreOrderHasItemPK.class);
    }

    /**
     * Save list of Pre-Order Items.
     * @param preOrderId
     * @param preOrderHasItemList
     */
    public void save(Long preOrderId, List<PreOrderHasItem> preOrderHasItemList) {

        // set pre order id for each item and save it.
        for (PreOrderHasItem preOrderHasItem : preOrderHasItemList) {

            preOrderHasItem.getPreOrderHasItemPK().setPreOrder(preOrderId);
            save(preOrderHasItem);
        }
    }
}
