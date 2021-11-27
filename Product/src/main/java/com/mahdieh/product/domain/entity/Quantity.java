package com.mahdieh.product.domain.entity;
/*
  @project DDD
  @Author Mahdieh Parhizkari
  @Date 11/13/21
  @Time 10:09 AM
  Created by Intellije IDEA
  Description: JPA - Criteria
*/

import lombok.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Quantity implements Serializable {
    private Integer productpk;
    private Integer quantity;
}
