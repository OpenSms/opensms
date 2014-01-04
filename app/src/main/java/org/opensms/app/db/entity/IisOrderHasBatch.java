/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opensms.app.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author dewmal
 */
@Entity
@Table(name = "iis_order_has_batch", catalog = "opensms", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IisOrderHasBatch.findAll", query = "SELECT i FROM IisOrderHasBatch i"),
    @NamedQuery(name = "IisOrderHasBatch.findByIisOrder", query = "SELECT i FROM IisOrderHasBatch i WHERE i.iisOrderHasBatchPK.iisOrder = :iisOrder"),
    @NamedQuery(name = "IisOrderHasBatch.findByBatch", query = "SELECT i FROM IisOrderHasBatch i WHERE i.iisOrderHasBatchPK.batch = :batch"),
    @NamedQuery(name = "IisOrderHasBatch.findByIssuedQuantity", query = "SELECT i FROM IisOrderHasBatch i WHERE i.issuedQuantity = :issuedQuantity"),
    @NamedQuery(name = "IisOrderHasBatch.findByReturnQuantity", query = "SELECT i FROM IisOrderHasBatch i WHERE i.returnQuantity = :returnQuantity")})
public class IisOrderHasBatch implements Serializable, EntityInterface<IisOrderHasBatchPK> {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected IisOrderHasBatchPK iisOrderHasBatchPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "issued_quantity")
    private BigDecimal issuedQuantity;
    @Column(name = "return_quantity")
    private BigDecimal returnQuantity;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iisOrderHasBatch", fetch = FetchType.LAZY)
    private List<IisOrderBatchHasGsrOrder> iisOrderBatchHasGsrOrderList;
    @JoinColumn(name = "batch", referencedColumnName = "batch_code", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Batch batch1;

    public IisOrderHasBatch() {
    }

    public IisOrderHasBatch(IisOrderHasBatchPK iisOrderHasBatchPK) {
        this.iisOrderHasBatchPK = iisOrderHasBatchPK;
    }

    public IisOrderHasBatch(IisOrderHasBatchPK iisOrderHasBatchPK, BigDecimal issuedQuantity) {
        this.iisOrderHasBatchPK = iisOrderHasBatchPK;
        this.issuedQuantity = issuedQuantity;
    }

    public IisOrderHasBatch(long iisOrder, String batch) {
        this.iisOrderHasBatchPK = new IisOrderHasBatchPK(iisOrder, batch);
    }

    public IisOrderHasBatchPK getIisOrderHasBatchPK() {
        return iisOrderHasBatchPK;
    }

    public void setIisOrderHasBatchPK(IisOrderHasBatchPK iisOrderHasBatchPK) {
        this.iisOrderHasBatchPK = iisOrderHasBatchPK;
    }

    public BigDecimal getIssuedQuantity() {
        return issuedQuantity;
    }

    public void setIssuedQuantity(BigDecimal issuedQuantity) {
        this.issuedQuantity = issuedQuantity;
    }

    public BigDecimal getReturnQuantity() {
        return returnQuantity;
    }

    public void setReturnQuantity(BigDecimal returnQuantity) {
        this.returnQuantity = returnQuantity;
    }

    @XmlTransient
    @JsonIgnore
    public List<IisOrderBatchHasGsrOrder> getIisOrderBatchHasGsrOrderList() {
        return iisOrderBatchHasGsrOrderList;
    }

    public void setIisOrderBatchHasGsrOrderList(List<IisOrderBatchHasGsrOrder> iisOrderBatchHasGsrOrderList) {
        this.iisOrderBatchHasGsrOrderList = iisOrderBatchHasGsrOrderList;
    }

    public Batch getBatch1() {
        return batch1;
    }

    public void setBatch1(Batch batch1) {
        this.batch1 = batch1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iisOrderHasBatchPK != null ? iisOrderHasBatchPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IisOrderHasBatch)) {
            return false;
        }
        IisOrderHasBatch other = (IisOrderHasBatch) object;
        if ((this.iisOrderHasBatchPK == null && other.iisOrderHasBatchPK != null) || (this.iisOrderHasBatchPK != null && !this.iisOrderHasBatchPK.equals(other.iisOrderHasBatchPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.opensms.app.db.entity.IisOrderHasBatch[ iisOrderHasBatchPK=" + iisOrderHasBatchPK + " ]";
    }

    @Override
    public IisOrderHasBatchPK getId() {
        return getIisOrderHasBatchPK();
    }
}
