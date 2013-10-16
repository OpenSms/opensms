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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * @author dewmal
 */
@Entity
@Table(name = "role")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Role.findAll", query = "SELECT r FROM Role r"),
        @NamedQuery(name = "Role.findByRoleId", query = "SELECT r FROM Role r WHERE r.roleId = :roleId"),
        @NamedQuery(name = "Role.findByDescription", query = "SELECT r FROM Role r WHERE r.description = :description")})
public class Role implements Serializable, EntityInterface<Integer> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "role_id")
    private Integer roleId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "description")
    private String description;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "role1", fetch = FetchType.LAZY)
    private List<UserRole> userRoleList;

    public Role() {
    }

    public Role(Integer roleId) {
        this.roleId = roleId;
    }

    public Role(Integer roleId, String description) {
        this.roleId = roleId;
        this.description = description;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    @JsonIgnore
    public List<UserRole> getUserRoleList() {
        return userRoleList;
    }

    public void setUserRoleList(List<UserRole> userRoleList) {
        this.userRoleList = userRoleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roleId != null ? roleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Role)) {
            return false;
        }
        Role other = (Role) object;
        if ((this.roleId == null && other.roleId != null) || (this.roleId != null && !this.roleId.equals(other.roleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.opensms.app.db.entity.Role[ roleId=" + roleId + " ]";
    }

    @Override
    public Integer getId() {
        return getRoleId();
    }

    public enum ROLES {
        ADMINISTRATOR_ROLE(1, "Administrator"),
        MANAGER_ROLE(2, "Manager"),
        SALES_REPRESENTATIVE_ROLE(3, "Sales Representative"),
        CUSTOMER_ROLE(4, "Customer"),
        VENDOR_ROLE(5, "Vendor");

        private Role role;

        ROLES(int id, String description) {
            role = new Role(id, description);
        }

        public Role getRole() {
            return role;
        }

        @Override
        public String toString() {
            return role.getDescription();
        }
    }
}
