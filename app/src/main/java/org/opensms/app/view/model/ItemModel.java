package org.opensms.app.view.model;

import org.opensms.app.db.entity.Item;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: sadika
 * Date: 11/16/13
 * Time: 12:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class ItemModel {

    private Item item;
    private BigDecimal quantity;

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
