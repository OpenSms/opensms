package org.opensms.app.view.model;

import org.opensms.app.db.entity.IisOrder;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sadika
 * Date: 11/16/13
 * Time: 1:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class IisOrderModel {
    private IisOrder iisOrder;
    private List<ItemModel> itemModelList;

    public IisOrder getIisOrder() {
        return iisOrder;
    }

    public void setIisOrder(IisOrder iisOrder) {
        this.iisOrder = iisOrder;
    }

    public List<ItemModel> getItemModelList() {
        return itemModelList;
    }

    public void setItemModelList(List<ItemModel> itemModelList) {
        this.itemModelList = itemModelList;
    }
}
