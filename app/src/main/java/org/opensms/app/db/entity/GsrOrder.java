/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opensms.app.db.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author dewmal
 */
@Entity
@Table(name = "gsr_order")
@NamedQueries({
    @NamedQuery(name = "GsrOrder.findAll", query = "SELECT g FROM GsrOrder g"),
    @NamedQuery(name = "GsrOrder.findByGsrOrderId", query = "SELECT g FROM GsrOrder g WHERE g.gsrOrderId = :gsrOrderId"),
    @NamedQuery(name = "GsrOrder.findBySellingDateTime", query = "SELECT g FROM GsrOrder g WHERE g.sellingDateTime = :sellingDateTime")})
public class GsrOrder implements EntityInterface<Long>, Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "gsr_order_id")
    private Long gsrOrderId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "selling_date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sellingDateTime;
    @JoinColumn(name = "customer", referencedColumnName = "user_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Customer customer;
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gsrOrder1", fetch = FetchType.EAGER)
    private List<IisOrderBatchHasGsrOrder> iisOrderBatchHasGsrOrderList;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gsrOrder", fetch = FetchType.LAZY)
//    private List<GsrPayment> gsrPaymentList;

    public GsrOrder() {
    }

    public GsrOrder(Long gsrOrderId) {
        this.gsrOrderId = gsrOrderId;
    }

    public GsrOrder(Long gsrOrderId, Date sellingDateTime) {
        this.gsrOrderId = gsrOrderId;
        this.sellingDateTime = sellingDateTime;
    }

    public Long getGsrOrderId() {
        return gsrOrderId;
    }

    public void setGsrOrderId(Long gsrOrderId) {
        this.gsrOrderId = gsrOrderId;
    }

    public Date getSellingDateTime() {
        return sellingDateTime;
    }

    public void setSellingDateTime(Date sellingDateTime) {
        this.sellingDateTime = sellingDateTime;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

//    @XmlTransient

    public List<IisOrderBatchHasGsrOrder> getIisOrderBatchHasGsrOrderList() {
        return iisOrderBatchHasGsrOrderList;
    }

    public void setIisOrderBatchHasGsrOrderList(List<IisOrderBatchHasGsrOrder> iisOrderBatchHasGsrOrderList) {
        this.iisOrderBatchHasGsrOrderList = iisOrderBatchHasGsrOrderList;
    }
//
//    @XmlTransient
//    @JsonIgnore
//    public List<GsrPayment> getGsrPaymentList() {
//        return gsrPaymentList;
//    }
//
//    public void setGsrPaymentList(List<GsrPayment> gsrPaymentList) {
//        this.gsrPaymentList = gsrPaymentList;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gsrOrderId != null ? gsrOrderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GsrOrder)) {
            return false;
        }
        GsrOrder other = (GsrOrder) object;
        if ((this.gsrOrderId == null && other.gsrOrderId != null) || (this.gsrOrderId != null && !this.gsrOrderId.equals(other.gsrOrderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.opensms.app.db.entity.GsrOrder[ gsrOrderId=" + gsrOrderId + " ]";
    }

    @Override
    public Long getId() {
        return gsrOrderId;
    }
}
