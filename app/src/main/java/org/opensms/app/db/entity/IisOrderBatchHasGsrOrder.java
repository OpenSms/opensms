/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opensms.app.db.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author dewmal
 */
@Entity
@Table(name = "iis_order_batch_has_gsr_order", catalog = "opensms", schema = "")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "IisOrderBatchHasGsrOrder.findAll", query = "SELECT i FROM IisOrderBatchHasGsrOrder i"),
        @NamedQuery(name = "IisOrderBatchHasGsrOrder.findByGsrOrder", query = "SELECT i FROM IisOrderBatchHasGsrOrder i WHERE i.iisOrderBatchHasGsrOrderPK.gsrOrder = :gsrOrder"),
        @NamedQuery(name = "IisOrderBatchHasGsrOrder.findByIisOrder", query = "SELECT i FROM IisOrderBatchHasGsrOrder i WHERE i.iisOrderBatchHasGsrOrderPK.iisOrder = :iisOrder"),
        @NamedQuery(name = "IisOrderBatchHasGsrOrder.findByBatch", query = "SELECT i FROM IisOrderBatchHasGsrOrder i WHERE i.iisOrderBatchHasGsrOrderPK.batch = :batch"),
        @NamedQuery(name = "IisOrderBatchHasGsrOrder.findByQuantity", query = "SELECT i FROM IisOrderBatchHasGsrOrder i WHERE i.quantity = :quantity")})
public class IisOrderBatchHasGsrOrder implements EntityInterface<IisOrderBatchHasGsrOrderPK>, Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected IisOrderBatchHasGsrOrderPK iisOrderBatchHasGsrOrderPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantity")
    private BigDecimal quantity;
    @JsonBackReference
    @JoinColumn(name = "gsr_order", referencedColumnName = "gsr_order_id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GsrOrder gsrOrder1;
    @JoinColumns({
            @JoinColumn(name = "iis_order", referencedColumnName = "iis_order", insertable = false, updatable = false),
            @JoinColumn(name = "batch", referencedColumnName = "batch", insertable = false, updatable = false)})
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private IisOrderHasBatch iisOrderHasBatch;

    public IisOrderBatchHasGsrOrder() {
    }

    public IisOrderBatchHasGsrOrder(IisOrderBatchHasGsrOrderPK iisOrderBatchHasGsrOrderPK) {
        this.iisOrderBatchHasGsrOrderPK = iisOrderBatchHasGsrOrderPK;
    }

    public IisOrderBatchHasGsrOrder(IisOrderBatchHasGsrOrderPK iisOrderBatchHasGsrOrderPK, BigDecimal quantity) {
        this.iisOrderBatchHasGsrOrderPK = iisOrderBatchHasGsrOrderPK;
        this.quantity = quantity;
    }

    public IisOrderBatchHasGsrOrder(long gsrOrder, long iisOrder, String batch) {
        this.iisOrderBatchHasGsrOrderPK = new IisOrderBatchHasGsrOrderPK(gsrOrder, iisOrder, batch);
    }

    public IisOrderBatchHasGsrOrderPK getIisOrderBatchHasGsrOrderPK() {
        return iisOrderBatchHasGsrOrderPK;
    }

    public void setIisOrderBatchHasGsrOrderPK(IisOrderBatchHasGsrOrderPK iisOrderBatchHasGsrOrderPK) {
        this.iisOrderBatchHasGsrOrderPK = iisOrderBatchHasGsrOrderPK;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }


    public GsrOrder getGsrOrder1() { return gsrOrder1; }

    public void setGsrOrder1(GsrOrder gsrOrder1) { this.gsrOrder1 = gsrOrder1; }

    public IisOrderHasBatch getIisOrderHasBatch() {
        return iisOrderHasBatch;
    }

    public void setIisOrderHasBatch(IisOrderHasBatch iisOrderHasBatch) {
        this.iisOrderHasBatch = iisOrderHasBatch;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iisOrderBatchHasGsrOrderPK != null ? iisOrderBatchHasGsrOrderPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IisOrderBatchHasGsrOrder)) {
            return false;
        }
        IisOrderBatchHasGsrOrder other = (IisOrderBatchHasGsrOrder) object;
        if ((this.iisOrderBatchHasGsrOrderPK == null && other.iisOrderBatchHasGsrOrderPK != null) || (this.iisOrderBatchHasGsrOrderPK != null && !this.iisOrderBatchHasGsrOrderPK.equals(other.iisOrderBatchHasGsrOrderPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.opensms.app.db.entity.IisOrderBatchHasGsrOrder[ iisOrderBatchHasGsrOrderPK=" + iisOrderBatchHasGsrOrderPK + " ]";
    }

    @Override
    public IisOrderBatchHasGsrOrderPK getId() {
        return getIisOrderBatchHasGsrOrderPK();
    }
}
