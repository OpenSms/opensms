package org.opensms.app.view.model;

import org.opensms.app.db.entity.PreOrder;
import org.opensms.app.db.entity.PreOrderHasItem;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sadika
 * Date: 11/6/13
 * Time: 9:19 PM
 * To change this template use File | Settings | File Templates.
 */

/**
 *  PreOrderModel: use to send pre order data to preOrderSave() in PreOrderController
 */
public class PreOrderModel {
    private PreOrder preOrder;
    private List<PreOrderHasItem> preOrderHasItemList;

    public List<PreOrderHasItem> getPreOrderHasItemList() {
        return preOrderHasItemList;
    }

    public void setPreOrderHasItemList(List<PreOrderHasItem> preOrderHasItemList) {
        this.preOrderHasItemList = preOrderHasItemList;
    }

    public PreOrder getPreOrder() {
        return preOrder;
    }

    public void setPreOrder(PreOrder preOrder) {
        this.preOrder = preOrder;
    }
}
