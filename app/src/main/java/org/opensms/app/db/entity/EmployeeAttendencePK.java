/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opensms.app.db.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author sadika
 */
@Embeddable
public class EmployeeAttendencePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private int userId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "signin_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date signinTime;

    public EmployeeAttendencePK() {
    }

    public EmployeeAttendencePK(int userId, Date signinTime) {
        this.userId = userId;
        this.signinTime = signinTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getSigninTime() {
        return signinTime;
    }

    public void setSigninTime(Date signinTime) {
        this.signinTime = signinTime;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) userId;
        hash += (signinTime != null ? signinTime.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmployeeAttendencePK)) {
            return false;
        }
        EmployeeAttendencePK other = (EmployeeAttendencePK) object;
        if (this.userId != other.userId) {
            return false;
        }
        if ((this.signinTime == null && other.signinTime != null) || (this.signinTime != null && !this.signinTime.equals(other.signinTime))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.opensms.app.db.entity.EmployeeAttendencePK[ userId=" + userId + ", signinTime=" + signinTime + " ]";
    }
    
}
