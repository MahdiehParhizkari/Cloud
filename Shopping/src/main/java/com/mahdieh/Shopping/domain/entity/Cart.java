package com.mahdieh.Shopping.domain.entity;
/*
  @project DDD
  @Author Mahdieh Parhizkari
  @Date 11/17/21
  @Time 10:16 AM
  Created by Intellije IDEA
  Description: RootAggregate=RootEntity
*/

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "cart")
@IdClass(CartPK.class)
public class Cart implements Serializable {
    private Integer customerfk;
    private Integer productfk;
    private Integer quantity;
    private BigDecimal price;

    @Id
    @Column(name = "customerfk")
    @NotNull(message = "Customer Code should not be empty")
    public Integer getCustomerfk() {return customerfk;}
    public void setCustomerfk(Integer customerfk) {this.customerfk = customerfk;}

    @Id
    @Column(name = "productfk")
    @NotNull(message = "Product Code should not be empty")
    public Integer getProductfk() {return productfk;}
    public void setProductfk(Integer productfk) {this.productfk = productfk;}

    @Column(name = "quantity")
    @NotNull(message = "quantity should not be empty")
    public Integer getQuantity() {return quantity;}
    public void setQuantity(Integer quantity) {this.quantity = quantity;}

    @Column(name = "price")
    @NotNull(message = "price should not be empty")
    public BigDecimal getPrice() {return price;}
    public void setPrice(BigDecimal price) {this.price = price;}

}
