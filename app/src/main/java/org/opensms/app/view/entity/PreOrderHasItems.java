package org.opensms.app.view.entity;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by dewmal on 1/6/14.
 */
public class PreOrderHasItems {

    private List<PreOrderHasItem> orderHasItems=new ArrayList<PreOrderHasItem>();

    public void insertPreOrderHasItem(org.opensms.app.db.entity.PreOrderHasItem preOrderHasItem){
        PreOrderHasItem hasItem=new PreOrderHasItem();
        hasItem.setItemId(preOrderHasItem.getId().getItem());
        hasItem.setPreOrderId(preOrderHasItem.getId().getPreOrder()+"");
        hasItem.setQty(preOrderHasItem.getQuantity().doubleValue());

        orderHasItems.add(hasItem);
    }

    public List<PreOrderHasItem> getOrderHasItems() {
        return orderHasItems;
    }

    public void setOrderHasItems(List<PreOrderHasItem> orderHasItems) {
        this.orderHasItems = orderHasItems;
    }
}
