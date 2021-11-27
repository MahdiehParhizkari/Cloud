package com.mahdieh.product.domain.entity;
/*
  @project DDD
  @Author Mahdieh Parhizkari
  @Date 11/13/21
  @Time 10:05 AM
  Created by Intellije IDEA
  Description: Entity Object
*/

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Category {
    private Integer categorypk;
    private String categoryname;
    private String categorydescription;
    private List<Product> productsByCategorypk;

    @Id
    @Column(name = "categorypk")
    @GeneratedValue(strategy=GenerationType.IDENTITY) //specify the generation strategy used for the primary key.
    public Integer getCategorypk() {
        return categorypk;
    }
    public void setCategorypk(Integer categorypk) {
        this.categorypk = categorypk;
    }

    @Basic
    @Column(name = "categoryname")
    public String getCategoryname() {
        return categoryname;
    }
    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    @Basic
    @Column(name = "categorydescription")
    public String getCategorydescription() {
        return categorydescription;
    }
    public void setCategorydescription(String categorydescription) {
        this.categorydescription = categorydescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(categorypk, category.categorypk) && Objects.equals(categoryname, category.categoryname) && Objects.equals(categorydescription, category.categorydescription);
    }
    @Override
    public int hashCode() {
        return Objects.hash(categorypk, categoryname, categorydescription);
    }

    @OneToMany(mappedBy = "categoryByCategoryfk")
    @JsonIgnore
    public List<Product> getProductsByCategorypk() {
        return productsByCategorypk;
    }
    public void setProductsByCategorypk(List<Product> productsByCategorypk) {
        this.productsByCategorypk = productsByCategorypk;
    }
}
