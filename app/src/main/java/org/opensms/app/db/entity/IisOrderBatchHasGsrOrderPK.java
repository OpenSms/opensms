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
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

/**
 *
 * @author dewmal
 */
@Embeddable
public class IisOrderBatchHasGsrOrderPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "iis_order")
    private long iisOrder;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "batch")
    private byte[] batch;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gsr_order")
    private long gsrOrder;

    public IisOrderBatchHasGsrOrderPK() {
    }

    public IisOrderBatchHasGsrOrderPK(long iisOrder, byte[] batch, long gsrOrder) {
        this.iisOrder = iisOrder;
        this.batch = batch;
        this.gsrOrder = gsrOrder;
    }

    public long getIisOrder() {
        return iisOrder;
    }

    public void setIisOrder(long iisOrder) {
        this.iisOrder = iisOrder;
    }

    public byte[] getBatch() {
        return batch;
    }

    public void setBatch(byte[] batch) {
        this.batch = batch;
    }

    public long getGsrOrder() {
        return gsrOrder;
    }

    public void setGsrOrder(long gsrOrder) {
        this.gsrOrder = gsrOrder;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) iisOrder;
        hash += (batch != null ? batch.hashCode() : 0);
        hash += (int) gsrOrder;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IisOrderBatchHasGsrOrderPK)) {
            return false;
        }
        IisOrderBatchHasGsrOrderPK other = (IisOrderBatchHasGsrOrderPK) object;
        if (this.iisOrder != other.iisOrder) {
            return false;
        }
        if ((this.batch == null && other.batch != null) || (this.batch != null && !this.batch.equals(other.batch))) {
            return false;
        }
        if (this.gsrOrder != other.gsrOrder) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.opensms.app.db.entity.IisOrderBatchHasGsrOrderPK[ iisOrder=" + iisOrder + ", batch=" + batch + ", gsrOrder=" + gsrOrder + " ]";
    }
    
}
