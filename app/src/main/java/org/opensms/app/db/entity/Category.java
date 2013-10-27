/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opensms.app.db.entity;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;

/**
 * @author dewmal
 */
@Entity
@Table(name = "category")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c"),
        @NamedQuery(name = "Category.findByCategoryId", query = "SELECT c FROM Category c WHERE c.categoryId = :categoryId"),
        @NamedQuery(name = "Category.findByCategory", query = "SELECT c FROM Category c WHERE c.category = :category")})
public class Category implements Serializable, EntityInterface<Integer> {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "category_id")
    private Integer categoryId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "category")
    private String category;
    @JoinTable(name = "category_has_item", joinColumns = {
            @JoinColumn(name = "category", referencedColumnName = "category_id")}, inverseJoinColumns = {
            @JoinColumn(name = "item", referencedColumnName = "item_id")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Item> itemList;
    @JoinColumn(name = "parent_category", referencedColumnName = "category_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Category parentCategory;

    public Category() {
    }

    public Category(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Category(Integer categoryId, String category) {
        this.categoryId = categoryId;
        this.category = category;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @XmlTransient
    @JsonIgnore
    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }



    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (categoryId != null ? categoryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Category)) {
            return false;
        }
        Category other = (Category) object;
        if ((this.categoryId == null && other.categoryId != null) || (this.categoryId != null && !this.categoryId.equals(other.categoryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.opensms.app.db.entity.CategoryController[ categoryId=" + categoryId + " ]";
    }

    @Override
    public Integer getId() {
        return getCategoryId();
    }
}
