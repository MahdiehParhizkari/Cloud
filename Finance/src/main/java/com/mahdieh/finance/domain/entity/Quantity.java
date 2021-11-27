package com.mahdieh.finance.domain.entity;
/*
  @project DDD
  @Author Mahdieh Parhizkari
  @Date 11/17/21
  @Time 3:04 PM
  Created by Intellije IDEA
  Description:
*/
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Quantity {
    private Integer productpk;
    private Integer quantity;
}
