/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opensms.app.db.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dewmal
 */
@Entity
@Table(name = "batch", catalog = "opensms", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Batch.findAll", query = "SELECT b FROM Batch b"),
    @NamedQuery(name = "Batch.findByBatchCode", query = "SELECT b FROM Batch b WHERE b.batchCode = :batchCode"),
    @NamedQuery(name = "Batch.findByBuyingUnitPrice", query = "SELECT b FROM Batch b WHERE b.buyingUnitPrice = :buyingUnitPrice"),
    @NamedQuery(name = "Batch.findByQuantity", query = "SELECT b FROM Batch b WHERE b.quantity = :quantity")})
public class Batch implements Serializable ,EntityInterface<String>{
    private static final long serialVersionUID = 1L;
    @Id
    @GenericGenerator(name="seq_id",strategy = "org.opensms.app.db.entity.helper.BatchIdGenerator")
    @GeneratedValue(generator="seq_id")
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "batch_code")
    private String batchCode;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "buying_unit_price")
    private BigDecimal buyingUnitPrice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantity")
    private BigDecimal quantity;
    @JoinColumn(name = "profit", referencedColumnName = "profit_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Profit profit;
    @JoinColumn(name = "item", referencedColumnName = "item_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Item item;
    @JoinColumn(name = "grn_order", referencedColumnName = "grn_order_id")
    @JsonBackReference
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private GrnOrder grnOrder;
    @Basic(optional = true)
    @Column(name = "expire_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expireDate;
    @Basic(optional = true)
    @Column(name = "remaining_quantity")
    private BigDecimal remainingQuantity;

    public Batch() {
    }

    public Batch(String batchCode) {
        this.batchCode = batchCode;
    }

    public Batch(String batchCode, BigDecimal buyingUnitPrice, BigDecimal quantity) {
        this.batchCode = batchCode;
        this.buyingUnitPrice = buyingUnitPrice;
        this.quantity = quantity;
    }

    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
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

    public GrnOrder getGrnOrder() {
        return grnOrder;
    }

    public void setGrnOrder(GrnOrder grnOrder) {
        this.grnOrder = grnOrder;
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

    @Override
    public String getId() {
       return getBatchCode();
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public BigDecimal getRemainingQuantity() {
        return remainingQuantity;
    }

    public void setRemainingQuantity(BigDecimal remainingQuantity) {
        this.remainingQuantity = remainingQuantity;
    }
}
