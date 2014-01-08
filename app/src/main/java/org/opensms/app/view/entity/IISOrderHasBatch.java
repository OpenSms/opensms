package org.opensms.app.view.entity;

import org.opensms.app.db.entity.IisOrderHasBatch;

/**
 * Created by dewmal on 1/5/14.
 */
public class IISOrderHasBatch {

    private String itemId;
    private String itemName;
    private String unit;
    private double price;
    private double qty;

    public IISOrderHasBatch(IisOrderHasBatch orderHasBatch) {
        this.itemId=orderHasBatch.getBatch1().getItem().getItemId();

    }

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
    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public String getUnit() {
        return unit;
    }
    public void setUnit(String unit) {
        this.unit = unit;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

}
