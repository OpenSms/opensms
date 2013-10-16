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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "employee")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e"),
    @NamedQuery(name = "Employee.findByUserId", query = "SELECT e FROM Employee e WHERE e.userId = :userId"),
    @NamedQuery(name = "Employee.findBySurname", query = "SELECT e FROM Employee e WHERE e.surname = :surname"),
    @NamedQuery(name = "Employee.findByInitials", query = "SELECT e FROM Employee e WHERE e.initials = :initials"),
    @NamedQuery(name = "Employee.findByNameReferredByInitials", query = "SELECT e FROM Employee e WHERE e.nameReferredByInitials = :nameReferredByInitials")})
public class Employee implements Serializable, EntityInterface<Integer> {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private Integer userId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "surname")
    private String surname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "initials")
    private String initials;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name_referred_by_initials")
    private String nameReferredByInitials;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cashierEmployee", fetch = FetchType.LAZY)
    private List<GrnPayment> grnPaymentList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dataEntryEmployee", fetch = FetchType.LAZY)
    private List<GrnOrder> grnOrderList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "salesEmployee", fetch = FetchType.LAZY)
    private List<IisOrder> iisOrderList;
    @OneToMany(mappedBy = "returnCheckEmployee", fetch = FetchType.LAZY)
    private List<IisOrder> iisOrderList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itemIssuerEmployee", fetch = FetchType.LAZY)
    private List<IisOrder> iisOrderList2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee", fetch = FetchType.LAZY)
    private List<EmployeeType> employeeTypeList;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private User user;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cashierEmployee", fetch = FetchType.LAZY)
    private List<GsrPayment> gsrPaymentList;

    public Employee() {
    }

    public Employee(Integer userId) {
        this.userId = userId;
    }

    public Employee(Integer userId, String surname, String initials, String nameReferredByInitials) {
        this.userId = userId;
        this.surname = surname;
        this.initials = initials;
        this.nameReferredByInitials = nameReferredByInitials;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public String getNameReferredByInitials() {
        return nameReferredByInitials;
    }

    public void setNameReferredByInitials(String nameReferredByInitials) {
        this.nameReferredByInitials = nameReferredByInitials;
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
    public List<GrnOrder> getGrnOrderList() {
        return grnOrderList;
    }

    public void setGrnOrderList(List<GrnOrder> grnOrderList) {
        this.grnOrderList = grnOrderList;
    }

    @XmlTransient
    @JsonIgnore
    public List<IisOrder> getIisOrderList() {
        return iisOrderList;
    }

    public void setIisOrderList(List<IisOrder> iisOrderList) {
        this.iisOrderList = iisOrderList;
    }

    @XmlTransient
    @JsonIgnore
    public List<IisOrder> getIisOrderList1() {
        return iisOrderList1;
    }

    public void setIisOrderList1(List<IisOrder> iisOrderList1) {
        this.iisOrderList1 = iisOrderList1;
    }

    @XmlTransient
    @JsonIgnore
    public List<IisOrder> getIisOrderList2() {
        return iisOrderList2;
    }

    public void setIisOrderList2(List<IisOrder> iisOrderList2) {
        this.iisOrderList2 = iisOrderList2;
    }

    @XmlTransient
    @JsonIgnore
    public List<EmployeeType> getEmployeeTypeList() {
        return employeeTypeList;
    }

    public void setEmployeeTypeList(List<EmployeeType> employeeTypeList) {
        this.employeeTypeList = employeeTypeList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.opensms.app.db.entity.Employee[ userId=" + userId + " ]";
    }

    @Override
    public Integer getId() {
        return getUserId();
    }
}
