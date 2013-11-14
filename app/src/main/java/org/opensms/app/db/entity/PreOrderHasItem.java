/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opensms.app.db.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dewmal
 */
@Entity
@Table(name = "pre_order_has_item")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PreOrderHasItem.findAll", query = "SELECT p FROM PreOrderHasItem p"),
    @NamedQuery(name = "PreOrderHasItem.findByItem", query = "SELECT p FROM PreOrderHasItem p WHERE p.preOrderHasItemPK.item = :item"),
    @NamedQuery(name = "PreOrderHasItem.findByPreOrder", query = "SELECT p FROM PreOrderHasItem p WHERE p.preOrderHasItemPK.preOrder = :preOrder"),
    @NamedQuery(name = "PreOrderHasItem.findByQuantity", query = "SELECT p FROM PreOrderHasItem p WHERE p.quantity = :quantity")})
public class PreOrderHasItem implements Serializable, EntityInterface<PreOrderHasItemPK> {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PreOrderHasItemPK preOrderHasItemPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantity")
    private BigDecimal quantity;
    @JoinColumn(name = "pre_order", referencedColumnName = "pre_order_id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PreOrder preOrder1;
    @JoinColumn(name = "item", referencedColumnName = "item_id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Item item1;

    public PreOrderHasItem() {
    }

    public PreOrderHasItem(PreOrderHasItemPK preOrderHasItemPK) {
        this.preOrderHasItemPK = preOrderHasItemPK;
    }

    public PreOrderHasItem(PreOrderHasItemPK preOrderHasItemPK, BigDecimal quantity) {
        this.preOrderHasItemPK = preOrderHasItemPK;
        this.quantity = quantity;
    }

    public PreOrderHasItem(String item, long preOrder) {
        this.preOrderHasItemPK = new PreOrderHasItemPK(item, preOrder);
    }

    public PreOrderHasItemPK getPreOrderHasItemPK() {
        return preOrderHasItemPK;
    }

    public void setPreOrderHasItemPK(PreOrderHasItemPK preOrderHasItemPK) {
        this.preOrderHasItemPK = preOrderHasItemPK;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public PreOrder getPreOrder1() {
        return preOrder1;
    }

    public void setPreOrder1(PreOrder preOrder1) {
        this.preOrder1 = preOrder1;
    }

    public Item getItem1() {
        return item1;
    }

    public void setItem1(Item item1) {
        this.item1 = item1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (preOrderHasItemPK != null ? preOrderHasItemPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PreOrderHasItem)) {
            return false;
        }
        PreOrderHasItem other = (PreOrderHasItem) object;
        if ((this.preOrderHasItemPK == null && other.preOrderHasItemPK != null) || (this.preOrderHasItemPK != null && !this.preOrderHasItemPK.equals(other.preOrderHasItemPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.opensms.app.db.entity.PreOrderHasItem[ preOrderHasItemPK=" + preOrderHasItemPK + " ]";
    }

    @Override
    public PreOrderHasItemPK getId() {
        return getPreOrderHasItemPK();
    }
}
