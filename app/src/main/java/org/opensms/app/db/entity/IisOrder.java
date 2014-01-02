/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opensms.app.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author dewmal
 */
@Entity
@Table(name = "iis_order")
@NamedQueries({
    @NamedQuery(name = "IisOrder.findAll", query = "SELECT i FROM IisOrder i"),
    @NamedQuery(name = "IisOrder.findByIisOrderId", query = "SELECT i FROM IisOrder i WHERE i.iisOrderId = :iisOrderId"),
    @NamedQuery(name = "IisOrder.findByIssOrderDateTime", query = "SELECT i FROM IisOrder i WHERE i.issOrderDateTime = :issOrderDateTime")})

public class IisOrder implements Serializable, EntityInterface<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iis_order_id")
    private Long iisOrderId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "iss_order_date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date issOrderDateTime;
    @OneToMany(mappedBy = "iisOrder", fetch = FetchType.LAZY)
    private List<PreOrder> preOrderList;
    @JoinColumn(name = "sales_employee", referencedColumnName = "user_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Employee salesEmployee;
    @JoinColumn(name = "return_check_employee", referencedColumnName = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Employee returnCheckEmployee;
    @JoinColumn(name = "item_issuer_employee", referencedColumnName = "user_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Employee itemIssuerEmployee;


    public IisOrder() {
    }

    public IisOrder(Long iisOrderId) {
        this.iisOrderId = iisOrderId;
    }

    public IisOrder(Long iisOrderId, Date issOrderDateTime) {
        this.iisOrderId = iisOrderId;
        this.issOrderDateTime = issOrderDateTime;
    }

    public Long getIisOrderId() {
        return iisOrderId;
    }

    public void setIisOrderId(Long iisOrderId) {
        this.iisOrderId = iisOrderId;
    }

    public Date getIssOrderDateTime() {
        return issOrderDateTime;
    }

    public void setIssOrderDateTime(Date issOrderDateTime) {
        this.issOrderDateTime = issOrderDateTime;
    }

    @JsonIgnore
    public List<PreOrder> getPreOrderList() {
        return preOrderList;
    }

    public void setPreOrderList(List<PreOrder> preOrderList) {
        this.preOrderList = preOrderList;
    }

    public Employee getSalesEmployee() {
        return salesEmployee;
    }

    public void setSalesEmployee(Employee salesEmployee) {
        this.salesEmployee = salesEmployee;
    }

    public Employee getReturnCheckEmployee() {
        return returnCheckEmployee;
    }

    public void setReturnCheckEmployee(Employee returnCheckEmployee) {
        this.returnCheckEmployee = returnCheckEmployee;
    }

    public Employee getItemIssuerEmployee() {
        return itemIssuerEmployee;
    }

    public void setItemIssuerEmployee(Employee itemIssuerEmployee) {
        this.itemIssuerEmployee = itemIssuerEmployee;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iisOrderId != null ? iisOrderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IisOrder)) {
            return false;
        }
        IisOrder other = (IisOrder) object;
        if ((this.iisOrderId == null && other.iisOrderId != null) || (this.iisOrderId != null && !this.iisOrderId.equals(other.iisOrderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.opensms.app.db.entity.IisOrder[ iisOrderId=" + iisOrderId + " ]";
    }

    @Override
    public Long getId() {
        return iisOrderId;
    }
}
