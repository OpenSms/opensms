/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opensms.app.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author dewmal
 */
@Entity
@Table(name = "profit")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Profit.findAll", query = "SELECT p FROM Profit p"),
        @NamedQuery(name = "Profit.findByProfitId", query = "SELECT p FROM Profit p WHERE p.profitId = :profitId"),
        @NamedQuery(name = "Profit.findByValue", query = "SELECT p FROM Profit p WHERE p.value = :value"),
        @NamedQuery(name = "Profit.findByType", query = "SELECT p FROM Profit p WHERE p.type = :type")})
public class Profit implements Serializable, EntityInterface<Integer> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "profit_id")
    private Integer profitId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "value")
    private BigDecimal value;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "type")
    private String type;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(mappedBy = "profit", fetch = FetchType.LAZY)
    private List<Batch> batchList;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "defaultProfit", fetch = FetchType.LAZY)
    private List<Item> itemList;

    public Profit() {
    }

    public Profit(Integer profitId) {
        this.profitId = profitId;
    }

    public Profit(Integer profitId, BigDecimal value, String type) {
        this.profitId = profitId;
        this.value = value;
        this.type = type;
    }

    public Integer getProfitId() {
        return profitId;
    }

    public void setProfitId(Integer profitId) {
        this.profitId = profitId;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlTransient
    @JsonIgnore
    public List<Batch> getBatchList() {
        return batchList;
    }

    public void setBatchList(List<Batch> batchList) {
        this.batchList = batchList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (profitId != null ? profitId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Profit)) {
            return false;
        }
        Profit other = (Profit) object;
        if ((this.profitId == null && other.profitId != null) || (this.profitId != null && !this.profitId.equals(other.profitId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.opensms.app.db.entity.Profit[ profitId=" + profitId + " ]";
    }

    @Override
    public Integer getId() {
        return getProfitId();
    }
}
