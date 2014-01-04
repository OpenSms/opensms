package org.opensms.app.view.entity;

/**
 * Created by dewmal on 1/3/14.
 */
public class Batch {

    private String item_id;
    private String batch_id;
    private double quntity;



    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getBatch_id() {
        return batch_id;
    }

    public void setBatch_id(String batch_id) {
        this.batch_id = batch_id;
    }

    public double getQuntity() {
        return quntity;
    }

    public void setQuntity(double quntity) {
        this.quntity = quntity;
    }
}
