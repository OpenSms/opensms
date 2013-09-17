/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opensms.app.db.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dewmal
 */
@Entity
@Table(name = "gsr_payment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GsrPayment.findAll", query = "SELECT g FROM GsrPayment g"),
    @NamedQuery(name = "GsrPayment.findByGsrPaymentId", query = "SELECT g FROM GsrPayment g WHERE g.gsrPaymentId = :gsrPaymentId"),
    @NamedQuery(name = "GsrPayment.findByAmount", query = "SELECT g FROM GsrPayment g WHERE g.amount = :amount"),
    @NamedQuery(name = "GsrPayment.findByPaymentDate", query = "SELECT g FROM GsrPayment g WHERE g.paymentDate = :paymentDate")})
public class GsrPayment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "gsr_payment_id")
    private Long gsrPaymentId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount")
    private BigDecimal amount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "payment_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date paymentDate;
    @JoinColumn(name = "gsr_order", referencedColumnName = "gsr_order_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GsrOrder gsrOrder;
    @JoinColumn(name = "cashier_employee", referencedColumnName = "user_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Employee cashierEmployee;
    @JoinColumn(name = "payment_method", referencedColumnName = "payment_method_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PaymentMethod paymentMethod;

    public GsrPayment() {
    }

    public GsrPayment(Long gsrPaymentId) {
        this.gsrPaymentId = gsrPaymentId;
    }

    public GsrPayment(Long gsrPaymentId, BigDecimal amount, Date paymentDate) {
        this.gsrPaymentId = gsrPaymentId;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }

    public Long getGsrPaymentId() {
        return gsrPaymentId;
    }

    public void setGsrPaymentId(Long gsrPaymentId) {
        this.gsrPaymentId = gsrPaymentId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public GsrOrder getGsrOrder() {
        return gsrOrder;
    }

    public void setGsrOrder(GsrOrder gsrOrder) {
        this.gsrOrder = gsrOrder;
    }

    public Employee getCashierEmployee() {
        return cashierEmployee;
    }

    public void setCashierEmployee(Employee cashierEmployee) {
        this.cashierEmployee = cashierEmployee;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gsrPaymentId != null ? gsrPaymentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GsrPayment)) {
            return false;
        }
        GsrPayment other = (GsrPayment) object;
        if ((this.gsrPaymentId == null && other.gsrPaymentId != null) || (this.gsrPaymentId != null && !this.gsrPaymentId.equals(other.gsrPaymentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.opensms.app.db.entity.GsrPayment[ gsrPaymentId=" + gsrPaymentId + " ]";
    }
    
}
