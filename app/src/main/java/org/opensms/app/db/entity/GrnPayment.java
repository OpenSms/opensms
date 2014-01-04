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
@Table(name = "grn_payment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GrnPayment.findAll", query = "SELECT g FROM GrnPayment g"),
    @NamedQuery(name = "GrnPayment.findByPayementId", query = "SELECT g FROM GrnPayment g WHERE g.payementId = :payementId"),
    @NamedQuery(name = "GrnPayment.findByAmount", query = "SELECT g FROM GrnPayment g WHERE g.amount = :amount"),
    @NamedQuery(name = "GrnPayment.findByPaymentDate", query = "SELECT g FROM GrnPayment g WHERE g.paymentDate = :paymentDate")})
public class GrnPayment implements Serializable, EntityInterface<Long>  {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "payement_id")
    private Long payementId;
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
    @JoinColumn(name = "payment_method", referencedColumnName = "payment_method_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private PaymentMethod paymentMethod;
    @JoinColumn(name = "grn_order", referencedColumnName = "grn_order_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private GrnOrder grnOrder;
    @JoinColumn(name = "cashier_employee", referencedColumnName = "user_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Employee cashierEmployee;

    public GrnPayment() {
    }

    public GrnPayment(Long payementId) {
        this.payementId = payementId;
    }

    public GrnPayment(Long payementId, BigDecimal amount, Date paymentDate) {
        this.payementId = payementId;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }

    public Long getPayementId() {
        return payementId;
    }

    public void setPayementId(Long payementId) {
        this.payementId = payementId;
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

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public GrnOrder getGrnOrder() {
        return grnOrder;
    }

    public void setGrnOrder(GrnOrder grnOrder) {
        this.grnOrder = grnOrder;
    }

    public Employee getCashierEmployee() {
        return cashierEmployee;
    }

    public void setCashierEmployee(Employee cashierEmployee) {
        this.cashierEmployee = cashierEmployee;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (payementId != null ? payementId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GrnPayment)) {
            return false;
        }
        GrnPayment other = (GrnPayment) object;
        if ((this.payementId == null && other.payementId != null) || (this.payementId != null && !this.payementId.equals(other.payementId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.opensms.app.db.entity.GrnPayment[ payementId=" + payementId + " ]";
    }

    @Override
    public Long getId() {
        return getPayementId();
    }
}
