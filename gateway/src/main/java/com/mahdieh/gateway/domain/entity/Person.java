package com.mahdieh.gateway.domain.entity;
/*
  @project DDD
  @Author Mahdieh Parhizkari
  @Date 12/10/21
  @Time 1:50 PM
  Created by Intellije IDEA
  Description:
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
