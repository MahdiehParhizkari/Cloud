package com.mahdieh.finance.infrastructure.resource;
/*
  @project DDD
  @Author Mahdieh Parhizkari
  @Date 12/4/21
  @Time 8:41 PM
  Created by Intellije IDEA
  Description: OpenFeign
*/

import com.mahdieh.finance.domain.entity.Quantity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

@FeignClient(name = "${people.app}", fallback = PeopleResFB.class)
public interface ProductRes {
    @PostMapping(value = "${product.service}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Quantity> getQuantity(List<Integer> inputValue);
}
