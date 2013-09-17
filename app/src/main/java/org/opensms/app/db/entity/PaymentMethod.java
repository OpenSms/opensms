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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "payment_method")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PaymentMethod.findAll", query = "SELECT p FROM PaymentMethod p"),
    @NamedQuery(name = "PaymentMethod.findByPaymentMethodId", query = "SELECT p FROM PaymentMethod p WHERE p.paymentMethodId = :paymentMethodId"),
    @NamedQuery(name = "PaymentMethod.findByDescription", query = "SELECT p FROM PaymentMethod p WHERE p.description = :description")})
public class PaymentMethod implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "payment_method_id")
    private Integer paymentMethodId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paymentMethod", fetch = FetchType.LAZY)
    private List<GrnPayment> grnPaymentList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paymentMethod", fetch = FetchType.LAZY)
    private List<GsrPayment> gsrPaymentList;

    public PaymentMethod() {
    }

    public PaymentMethod(Integer paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public PaymentMethod(Integer paymentMethodId, String description) {
        this.paymentMethodId = paymentMethodId;
        this.description = description;
    }

    public Integer getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(Integer paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    @JsonIgnore
    public List<GrnPayment> getGrnPaymentList() {
        return grnPaymentList;
    }

    public void setGrnPaymentList(List<GrnPayment> grnPaymentList) {
        this.grnPaymentList = grnPaymentList;
    }

    @XmlTransient
    @JsonIgnore
    public List<GsrPayment> getGsrPaymentList() {
        return gsrPaymentList;
    }

    public void setGsrPaymentList(List<GsrPayment> gsrPaymentList) {
        this.gsrPaymentList = gsrPaymentList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (paymentMethodId != null ? paymentMethodId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PaymentMethod)) {
            return false;
        }
        PaymentMethod other = (PaymentMethod) object;
        if ((this.paymentMethodId == null && other.paymentMethodId != null) || (this.paymentMethodId != null && !this.paymentMethodId.equals(other.paymentMethodId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.opensms.app.db.entity.PaymentMethod[ paymentMethodId=" + paymentMethodId + " ]";
    }
    
}
