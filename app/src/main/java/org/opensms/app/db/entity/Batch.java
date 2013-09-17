/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opensms.app.db.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author dewmal
 */
@Entity
@Table(name = "batch")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Batch.findAll", query = "SELECT b FROM Batch b"),
    @NamedQuery(name = "Batch.findByBuyingUnitPrice", query = "SELECT b FROM Batch b WHERE b.buyingUnitPrice = :buyingUnitPrice"),
    @NamedQuery(name = "Batch.findByQuantity", query = "SELECT b FROM Batch b WHERE b.quantity = :quantity")})
public class Batch implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "batch_code")
    private byte[] batchCode;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "buying_unit_price")
    private BigDecimal buyingUnitPrice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantity")
    private BigDecimal quantity;
    @JoinColumn(name = "grn_order", referencedColumnName = "grn_order_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GrnOrder grnOrder;
    @JoinColumn(name = "profit", referencedColumnName = "profit_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Profit profit;
    @JoinColumn(name = "item", referencedColumnName = "item_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Item item;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "batch1", fetch = FetchType.LAZY)
    private List<IisOrderHasBatch> iisOrderHasBatchList;

    public Batch() {
    }

    public Batch(byte[] batchCode) {
        this.batchCode = batchCode;
    }

    public Batch(byte[] batchCode, BigDecimal buyingUnitPrice, BigDecimal quantity) {
        this.batchCode = batchCode;
        this.buyingUnitPrice = buyingUnitPrice;
        this.quantity = quantity;
    }

    public byte[] getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(byte[] batchCode) {
        this.batchCode = batchCode;
    }

    public BigDecimal getBuyingUnitPrice() {
        return buyingUnitPrice;
    }

    public void setBuyingUnitPrice(BigDecimal buyingUnitPrice) {
        this.buyingUnitPrice = buyingUnitPrice;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public GrnOrder getGrnOrder() {
        return grnOrder;
    }

    public void setGrnOrder(GrnOrder grnOrder) {
        this.grnOrder = grnOrder;
    }

    public Profit getProfit() {
        return profit;
    }

    public void setProfit(Profit profit) {
        this.profit = profit;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @XmlTransient
    @JsonIgnore
    public List<IisOrderHasBatch> getIisOrderHasBatchList() {
        return iisOrderHasBatchList;
    }

    public void setIisOrderHasBatchList(List<IisOrderHasBatch> iisOrderHasBatchList) {
        this.iisOrderHasBatchList = iisOrderHasBatchList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (batchCode != null ? batchCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Batch)) {
            return false;
        }
        Batch other = (Batch) object;
        if ((this.batchCode == null && other.batchCode != null) || (this.batchCode != null && !this.batchCode.equals(other.batchCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.opensms.app.db.entity.Batch[ batchCode=" + batchCode + " ]";
    }
    
}
