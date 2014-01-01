/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opensms.app.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author dewmal
 */
@Entity
@Table(name = "user_contact_detail")
@NamedQueries({
        @NamedQuery(name = "UserContactDetail.findAll", query = "SELECT u FROM UserContactDetail u"),
        @NamedQuery(name = "UserContactDetail.findByUserId", query = "SELECT u FROM UserContactDetail u WHERE u.userId = :userId"),
        @NamedQuery(name = "UserContactDetail.findByName", query = "SELECT u FROM UserContactDetail u WHERE u.name = :name"),
        @NamedQuery(name = "UserContactDetail.findByAddressLine1", query = "SELECT u FROM UserContactDetail u WHERE u.addressLine1 = :addressLine1"),
        @NamedQuery(name = "UserContactDetail.findByAddressLine2", query = "SELECT u FROM UserContactDetail u WHERE u.addressLine2 = :addressLine2"),
        @NamedQuery(name = "UserContactDetail.findByCity", query = "SELECT u FROM UserContactDetail u WHERE u.city = :city"),
        @NamedQuery(name = "UserContactDetail.findByProvince", query = "SELECT u FROM UserContactDetail u WHERE u.province = :province"),
        @NamedQuery(name = "UserContactDetail.findByPostalCode", query = "SELECT u FROM UserContactDetail u WHERE u.postalCode = :postalCode"),
        @NamedQuery(name = "UserContactDetail.findByCountry", query = "SELECT u FROM UserContactDetail u WHERE u.country = :country"),
        @NamedQuery(name = "UserContactDetail.findByPhoneNumber", query = "SELECT u FROM UserContactDetail u WHERE u.phoneNumber = :phoneNumber"),
        @NamedQuery(name = "UserContactDetail.findByEmail", query = "SELECT u FROM UserContactDetail u WHERE u.email = :email")})
public class UserContactDetail implements Serializable, EntityInterface<Integer> {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private Integer userId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name")
    private String name;
    @Size(max = 100)
    @Column(name = "address_line1")
    private String addressLine1;
    @Size(max = 100)
    @Column(name = "address_line2")
    private String addressLine2;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "city")
    private String city;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "province")
    private String province;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "postal_code")
    private String postalCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "country")
    private String country;
    @Size(max = 15)
    @Column(name = "phone_number")
    private String phoneNumber;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(name = "email")
    private String email;
    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private User user;

    public UserContactDetail() {
    }

    public UserContactDetail(Integer userId) {
        this.userId = userId;
    }

    public UserContactDetail(Integer userId, String name, String city, String province, String postalCode, String country) {
        this.userId = userId;
        this.name = name;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
        this.country = country;
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

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        if (!(object instanceof UserContactDetail)) {
            return false;
        }
        UserContactDetail other = (UserContactDetail) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.opensms.app.db.entity.UserContactDetail[ userId=" + userId + " ]";
    }

    @Override
    public Integer getId() {
        return getUserId();
    }
}
