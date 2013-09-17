/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opensms.app.db.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dewmal
 */
@Entity
@Table(name = "employee_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmployeeType.findAll", query = "SELECT e FROM EmployeeType e"),
    @NamedQuery(name = "EmployeeType.findByUser", query = "SELECT e FROM EmployeeType e WHERE e.employeeTypePK.user = :user"),
    @NamedQuery(name = "EmployeeType.findByType", query = "SELECT e FROM EmployeeType e WHERE e.employeeTypePK.type = :type"),
    @NamedQuery(name = "EmployeeType.findByAssignDate", query = "SELECT e FROM EmployeeType e WHERE e.employeeTypePK.assignDate = :assignDate"),
    @NamedQuery(name = "EmployeeType.findByResignDate", query = "SELECT e FROM EmployeeType e WHERE e.resignDate = :resignDate")})
public class EmployeeType implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EmployeeTypePK employeeTypePK;
    @Column(name = "resign_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date resignDate;
    @JoinColumn(name = "type", referencedColumnName = "type_id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Type type1;
    @JoinColumn(name = "user", referencedColumnName = "user_id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Employee employee;

    public EmployeeType() {
    }

    public EmployeeType(EmployeeTypePK employeeTypePK) {
        this.employeeTypePK = employeeTypePK;
    }

    public EmployeeType(int user, int type, Date assignDate) {
        this.employeeTypePK = new EmployeeTypePK(user, type, assignDate);
    }

    public EmployeeTypePK getEmployeeTypePK() {
        return employeeTypePK;
    }

    public void setEmployeeTypePK(EmployeeTypePK employeeTypePK) {
        this.employeeTypePK = employeeTypePK;
    }

    public Date getResignDate() {
        return resignDate;
    }

    public void setResignDate(Date resignDate) {
        this.resignDate = resignDate;
    }

    public Type getType1() {
        return type1;
    }

    public void setType1(Type type1) {
        this.type1 = type1;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (employeeTypePK != null ? employeeTypePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmployeeType)) {
            return false;
        }
        EmployeeType other = (EmployeeType) object;
        if ((this.employeeTypePK == null && other.employeeTypePK != null) || (this.employeeTypePK != null && !this.employeeTypePK.equals(other.employeeTypePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.opensms.app.db.entity.EmployeeType[ employeeTypePK=" + employeeTypePK + " ]";
    }
    
}
