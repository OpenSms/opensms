/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opensms.app.db.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author dewmal
 */
@Entity
@Table(name = "item")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Item.findAll", query = "SELECT i FROM Item i"),
    @NamedQuery(name = "Item.findByItemId", query = "SELECT i FROM Item i WHERE i.itemId = :itemId")})
public class Item implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "item_id")
    private String itemId;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "name")
    private String name;
    @ManyToMany(mappedBy = "itemList", fetch = FetchType.LAZY)
    private List<Category> categoryList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item1", fetch = FetchType.LAZY)
    private List<PreOrderHasItem> preOrderHasItemList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item", fetch = FetchType.LAZY)
    private List<Batch> batchList;
    @JoinColumn(name = "default_profit", referencedColumnName = "profit_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Profit defaultProfit;
    @JoinColumn(name = "unit", referencedColumnName = "unit_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Unit unit;

    public Item() {
    }

    public Item(String itemId) {
        this.itemId = itemId;
    }

    public Item(String itemId, String name) {
        this.itemId = itemId;
        this.name = name;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    @JsonIgnore
    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    @XmlTransient
    @JsonIgnore
    public List<PreOrderHasItem> getPreOrderHasItemList() {
        return preOrderHasItemList;
    }

    public void setPreOrderHasItemList(List<PreOrderHasItem> preOrderHasItemList) {
        this.preOrderHasItemList = preOrderHasItemList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Batch> getBatchList() {
        return batchList;
    }

    public void setBatchList(List<Batch> batchList) {
        this.batchList = batchList;
    }

    public Profit getDefaultProfit() {
        return defaultProfit;
    }

    public void setDefaultProfit(Profit defaultProfit) {
        this.defaultProfit = defaultProfit;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemId != null ? itemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Item)) {
            return false;
        }
        Item other = (Item) object;
        if ((this.itemId == null && other.itemId != null) || (this.itemId != null && !this.itemId.equals(other.itemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.opensms.app.db.entity.Item[ itemId=" + itemId + " ]";
    }
    
}
