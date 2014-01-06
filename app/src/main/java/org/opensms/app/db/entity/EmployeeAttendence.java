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
 * @author sadika
 */
@Entity
@Table(name = "employee_attendence", catalog = "opensms", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmployeeAttendence.findAll", query = "SELECT e FROM EmployeeAttendence e"),
    @NamedQuery(name = "EmployeeAttendence.findByUserId", query = "SELECT e FROM EmployeeAttendence e WHERE e.employeeAttendencePK.userId = :userId"),
    @NamedQuery(name = "EmployeeAttendence.findBySigninTime", query = "SELECT e FROM EmployeeAttendence e WHERE e.employeeAttendencePK.signinTime = :signinTime"),
    @NamedQuery(name = "EmployeeAttendence.findByLeaveTime", query = "SELECT e FROM EmployeeAttendence e WHERE e.leaveTime = :leaveTime")})
public class EmployeeAttendence implements Serializable, EntityInterface<EmployeeAttendencePK>  {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EmployeeAttendencePK employeeAttendencePK;
    @Column(name = "leave_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date leaveTime;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Employee employee;

    public EmployeeAttendence() {
    }

    public EmployeeAttendence(EmployeeAttendencePK employeeAttendencePK) {
        this.employeeAttendencePK = employeeAttendencePK;
    }

    public EmployeeAttendence(int userId, Date signinTime) {
        this.employeeAttendencePK = new EmployeeAttendencePK(userId, signinTime);
    }

    public EmployeeAttendencePK getEmployeeAttendencePK() {
        return employeeAttendencePK;
    }

    public void setEmployeeAttendencePK(EmployeeAttendencePK employeeAttendencePK) {
        this.employeeAttendencePK = employeeAttendencePK;
    }

    public Date getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Date leaveTime) {
        this.leaveTime = leaveTime;
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
        hash += (employeeAttendencePK != null ? employeeAttendencePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmployeeAttendence)) {
            return false;
        }
        EmployeeAttendence other = (EmployeeAttendence) object;
        if ((this.employeeAttendencePK == null && other.employeeAttendencePK != null) || (this.employeeAttendencePK != null && !this.employeeAttendencePK.equals(other.employeeAttendencePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.opensms.app.db.entity.EmployeeAttendence[ employeeAttendencePK=" + employeeAttendencePK + " ]";
    }

    @Override
    public EmployeeAttendencePK getId() {
        return getEmployeeAttendencePK();
    }
}
