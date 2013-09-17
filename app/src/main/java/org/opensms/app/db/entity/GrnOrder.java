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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author dewmal
 */
@Entity
@Table(name = "grn_order")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GrnOrder.findAll", query = "SELECT g FROM GrnOrder g"),
    @NamedQuery(name = "GrnOrder.findByGrnOrderId", query = "SELECT g FROM GrnOrder g WHERE g.grnOrderId = :grnOrderId"),
    @NamedQuery(name = "GrnOrder.findByReceiveDate", query = "SELECT g FROM GrnOrder g WHERE g.receiveDate = :receiveDate")})
public class GrnOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "grn_order_id")
    private Long grnOrderId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "receive_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date receiveDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grnOrder", fetch = FetchType.LAZY)
    private List<GrnPayment> grnPaymentList;
    @JoinColumn(name = "vendor", referencedColumnName = "user_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Vendor vendor;
    @JoinColumn(name = "data_entry_employee", referencedColumnName = "user_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Employee dataEntryEmployee;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grnOrder", fetch = FetchType.LAZY)
    private List<Batch> batchList;

    public GrnOrder() {
    }

    public GrnOrder(Long grnOrderId) {
        this.grnOrderId = grnOrderId;
    }

    public GrnOrder(Long grnOrderId, Date receiveDate) {
        this.grnOrderId = grnOrderId;
        this.receiveDate = receiveDate;
    }

    public Long getGrnOrderId() {
        return grnOrderId;
    }

    public void setGrnOrderId(Long grnOrderId) {
        this.grnOrderId = grnOrderId;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    @XmlTransient
    @JsonIgnore
    public List<GrnPayment> getGrnPaymentList() {
        return grnPaymentList;
    }

    public void setGrnPaymentList(List<GrnPayment> grnPaymentList) {
        this.grnPaymentList = grnPaymentList;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public Employee getDataEntryEmployee() {
        return dataEntryEmployee;
    }

    public void setDataEntryEmployee(Employee dataEntryEmployee) {
        this.dataEntryEmployee = dataEntryEmployee;
    }

    @XmlTransient
    @JsonIgnore
    public List<Batch> getBatchList() {
        return batchList;
    }

    public void setBatchList(List<Batch> batchList) {
        this.batchList = batchList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (grnOrderId != null ? grnOrderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GrnOrder)) {
            return false;
        }
        GrnOrder other = (GrnOrder) object;
        if ((this.grnOrderId == null && other.grnOrderId != null) || (this.grnOrderId != null && !this.grnOrderId.equals(other.grnOrderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.opensms.app.db.entity.GrnOrder[ grnOrderId=" + grnOrderId + " ]";
    }
    
}
