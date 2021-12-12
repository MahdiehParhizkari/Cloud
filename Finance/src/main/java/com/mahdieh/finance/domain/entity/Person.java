package com.mahdieh.finance.domain.entity;/*
  @project DDD
  @Author Mahdieh Parhizkari
  @Date 12/4/21
  @Time 9:49 PM
  Created by Intellije IDEA
  Description: ValueObject
*/

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Person {
    private Integer personpk;
    private Integer persontypeid;
    private Integer typedetailid;
    private String nationalkey;
    private String lastname;
    private String firstname;
}
