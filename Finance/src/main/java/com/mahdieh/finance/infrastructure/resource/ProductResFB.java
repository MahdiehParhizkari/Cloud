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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductResFB implements ProductRes {
    public static final Logger logger = LoggerFactory.getLogger(ProductResFB.class);

    @Override
    public List<Quantity> getQuantity(List<Integer> inputValue) {
        logger.info("Quantity service Circuit is open");
        List<Quantity> quantities = new ArrayList<Quantity>();
        quantities.add(new Quantity(0, 0));
        return quantities;
    }
}
