/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opensms.app.db.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author dewmal
 */
@Embeddable
public class IisOrderBatchHasGsrOrderPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "gsr_order")
    private long gsrOrder;
    @Basic(optional = false)
    @NotNull
    @Column(name = "iis_order")
    private long iisOrder;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "batch")
    private String batch;

    public IisOrderBatchHasGsrOrderPK() {
    }

    public IisOrderBatchHasGsrOrderPK(long gsrOrder, long iisOrder, String batch) {
        this.gsrOrder = gsrOrder;
        this.iisOrder = iisOrder;
        this.batch = batch;
    }

    public long getGsrOrder() {
        return gsrOrder;
    }

    public void setGsrOrder(long gsrOrder) {
        this.gsrOrder = gsrOrder;
    }

    public long getIisOrder() {
        return iisOrder;
    }

    public void setIisOrder(long iisOrder) {
        this.iisOrder = iisOrder;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) gsrOrder;
        hash += (int) iisOrder;
        hash += (batch != null ? batch.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IisOrderBatchHasGsrOrderPK)) {
            return false;
        }
        IisOrderBatchHasGsrOrderPK other = (IisOrderBatchHasGsrOrderPK) object;
        if (this.gsrOrder != other.gsrOrder) {
            return false;
        }
        if (this.iisOrder != other.iisOrder) {
            return false;
        }
        if ((this.batch == null && other.batch != null) || (this.batch != null && !this.batch.equals(other.batch))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.opensms.app.db.entity.IisOrderBatchHasGsrOrderPK[ gsrOrder=" + gsrOrder + ", iisOrder=" + iisOrder + ", batch=" + batch + " ]";
    }
    
}
