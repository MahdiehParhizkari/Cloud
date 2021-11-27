package com.mahdieh.Shopping.domain.entity;
/*
  @project DDD
  @Author Mahdieh Parhizkari
  @Date 11/17/21
  @Time 10:16 AM
  Created by Intellije IDEA
  Description: JPA - Criteria
*/

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class CartPK implements Serializable {
    private Integer customerfk;
    private Integer productfk;

    @Column(name = "cartpk")
    @Id
    public Integer getCustomerfk() { return customerfk; }
    public void setCustomerfk(Integer customerfk) { this.customerfk = customerfk; }

    @Column(name = "productfk")
    @Id
    public Integer getProductfk() { return productfk; }
    public void setProductfk(Integer productfk) { this.productfk = productfk; }
}
