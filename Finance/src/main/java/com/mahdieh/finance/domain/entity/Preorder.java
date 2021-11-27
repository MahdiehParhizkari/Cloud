package com.mahdieh.finance.domain.entity;
/*
  @project DDD
  @Author Mahdieh Parhizkari
  @Date 11/17/21
  @Time 3:04 PM
  Created by Intellije IDEA
  Description:Value Object
*/

import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class Preorder implements Serializable {
    private Integer customerfk;
    private Integer productfk;
    private Integer quantity;
    private BigDecimal price;
}