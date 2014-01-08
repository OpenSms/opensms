package org.opensms.app.view.entity;

/**
 * Created by dewmal on 1/6/14.
 */
public class PreOrderHasItem {

    private double qty;
    private String itemId;
    private String preOrderId;

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getPreOrderId() {
        return preOrderId;
    }

    public void setPreOrderId(String preOrderId) {
        this.preOrderId = preOrderId;
    }
}
