package com.mahdieh.product.application;
/*
  @project DDD
  @Author Mahdieh Parhizkari
  @Date 11/14/21
  @Time 11:52 AM
  Created by Intellije IDEA
  Description:
*/

import com.mahdieh.product.domain.entity.Product;
import com.mahdieh.product.domain.entity.Quantity;
import java.util.ArrayList;
import java.util.List;

public class Utility {

    public static List<Quantity> Product2Quantity(List<Product> products){
        try{
            List<Quantity> quantities = new ArrayList<>();
            for(Product product:products)
                quantities.add(new Quantity(product.getProductpk(),product.getQuantity()));
            return quantities;
        }catch (Exception e) {return null;}
    }
}
