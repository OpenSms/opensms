package org.opensms.app.view.model;

import org.opensms.app.db.entity.Batch;
import org.opensms.app.db.entity.GrnOrder;
import org.opensms.app.db.entity.Vendor;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dewmal
 * Date: 11/15/13
 * Time: 3:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class GrnOrderModel {

    private Vendor vendor;
    private List<Batch> batchList;

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public List<Batch> getBatchList() {
        return batchList;
    }

    public void setBatchList(List<Batch> batchList) {
        this.batchList = batchList;
    }
}
