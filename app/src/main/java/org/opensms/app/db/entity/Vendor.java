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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author dewmal
 */
@Entity
@Table(name = "vendor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vendor.findAll", query = "SELECT v FROM Vendor v"),
    @NamedQuery(name = "Vendor.findByUserId", query = "SELECT v FROM Vendor v WHERE v.userId = :userId"),
    @NamedQuery(name = "Vendor.findByName", query = "SELECT v FROM Vendor v WHERE v.name = :name")})
public class Vendor implements Serializable, EntityInterface<Integer> {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private Integer userId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @Lob
    @Size(max = 65535)
    @Column(name = "remark")
    private String remark;

    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.EAGER)
    private User user;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vendor", fetch = FetchType.LAZY)
    private List<GrnOrder> grnOrderList;

    public Vendor() {
    }

    public Vendor(Integer userId) {
        this.userId = userId;
    }

    public Vendor(Integer userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @XmlTransient
    @JsonIgnore
    public List<GrnOrder> getGrnOrderList() {
        return grnOrderList;
    }

    public void setGrnOrderList(List<GrnOrder> grnOrderList) {
        this.grnOrderList = grnOrderList;
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
        if (!(object instanceof Vendor)) {
            return false;
        }
        Vendor other = (Vendor) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.opensms.app.db.entity.Vendor[ userId=" + userId + " ]";
    }

    @Override
    public Integer getId() {
        return getUserId();
    }
}
