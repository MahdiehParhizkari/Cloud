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
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductResFB implements ProductRes {

    public List<Quantity> getQuantity(List<Integer> inputValue) {
        System.out.println("Quantity service Circuit is open");
        List<Quantity> quantities = new ArrayList<>();
        quantities.add(new Quantity(0, 0));
        return quantities;
    }
}
