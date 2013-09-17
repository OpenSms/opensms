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
 * @author dewmal
 */
@Embeddable
public class EmployeeTypePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "user")
    private int user;
    @Basic(optional = false)
    @NotNull
    @Column(name = "type")
    private int type;
    @Basic(optional = false)
    @NotNull
    @Column(name = "assign_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date assignDate;

    public EmployeeTypePK() {
    }

    public EmployeeTypePK(int user, int type, Date assignDate) {
        this.user = user;
        this.type = type;
        this.assignDate = assignDate;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(Date assignDate) {
        this.assignDate = assignDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) user;
        hash += (int) type;
        hash += (assignDate != null ? assignDate.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmployeeTypePK)) {
            return false;
        }
        EmployeeTypePK other = (EmployeeTypePK) object;
        if (this.user != other.user) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        if ((this.assignDate == null && other.assignDate != null) || (this.assignDate != null && !this.assignDate.equals(other.assignDate))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.opensms.app.db.entity.EmployeeTypePK[ user=" + user + ", type=" + type + ", assignDate=" + assignDate + " ]";
    }
    
}
