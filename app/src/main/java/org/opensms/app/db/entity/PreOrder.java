/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opensms.app.db.entity;

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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Proxy;

/**
 *
 * @author dewmal
 */
@Entity
@Table(name = "pre_order")
@NamedQueries({
    @NamedQuery(name = "PreOrder.findAll", query = "SELECT p FROM PreOrder p"),
    @NamedQuery(name = "PreOrder.findByPreOrderId", query = "SELECT p FROM PreOrder p WHERE p.preOrderId = :preOrderId"),
    @NamedQuery(name = "PreOrder.findByPreOrderDate", query = "SELECT p FROM PreOrder p WHERE p.preOrderDate = :preOrderDate"),
    @NamedQuery(name = "PreOrder.findByPriority", query = "SELECT p FROM PreOrder p WHERE p.priority = :priority"),
    @NamedQuery(name = "PreOrder.findByIsOpen", query = "SELECT p FROM PreOrder p WHERE p.isOpen = :isOpen")})
@Proxy(lazy = false)
public class PreOrder implements Serializable, EntityInterface<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pre_order_id")
    private Long preOrderId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pre_order_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date preOrderDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "priority")
    private int priority;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_open")
    private boolean isOpen;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "preOrder1", fetch = FetchType.EAGER)
    private List<PreOrderHasItem> preOrderHasItemList;
    @JsonIgnore
    @JoinColumn(name = "iis_order", referencedColumnName = "iis_order_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private IisOrder iisOrder;
    @JoinColumn(name = "customer", referencedColumnName = "user_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Customer customer;

    public PreOrder() {
    }

    public PreOrder(Long preOrderId) {
        this.preOrderId = preOrderId;
    }

    public PreOrder(Long preOrderId, Date preOrderDate, int priority, boolean isOpen) {
        this.preOrderId = preOrderId;
        this.preOrderDate = preOrderDate;
        this.priority = priority;
        this.isOpen = isOpen;
    }

    public Long getPreOrderId() {
        return preOrderId;
    }

    public void setPreOrderId() {
        this.preOrderId = preOrderId;
    }

    public Date getPreOrderDate() {
        return preOrderDate;
    }

    public void setPreOrderDate(Date preOrderDate) {
        this.preOrderDate = preOrderDate;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    @JsonIgnore
    public List<PreOrderHasItem> getPreOrderHasItemList() {
        return preOrderHasItemList;
    }

    public void setPreOrderHasItemList(List<PreOrderHasItem> preOrderHasItemList) {
        this.preOrderHasItemList = preOrderHasItemList;
    }

    public IisOrder getIisOrder() {
        return iisOrder;
    }

    public void setIisOrder(IisOrder iisOrder) {
        this.iisOrder = iisOrder;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (preOrderId != null ? preOrderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PreOrder)) {
            return false;
        }
        PreOrder other = (PreOrder) object;
        if ((this.preOrderId == null && other.preOrderId != null) || (this.preOrderId != null && !this.preOrderId.equals(other.preOrderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.opensms.app.db.entity.PreOrder[ preOrderId=" + preOrderId + " ]";
    }

    @Override
    public Long getId() {
        return getPreOrderId();
    }
}
