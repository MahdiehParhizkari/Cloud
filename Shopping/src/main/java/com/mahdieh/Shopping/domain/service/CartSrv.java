package com.mahdieh.Shopping.domain.service;
/*
  @project DDD
  @Author Mahdieh Parhizkari
  @Date 11/17/21
  @Time 10:39 AM
  Created by Intellije IDEA
  Description: Logic
*/

import com.mahdieh.Shopping.domain.entity.Cart;
import com.mahdieh.Shopping.infrastructure.mq.OrderMq;
import com.mahdieh.Shopping.infrastructure.repository.CartDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class CartSrv {
    @Autowired private CartDao cartDao;
    @Autowired private OrderMq orderMq;

    public List<Cart> showCart() throws Exception {
        return cartDao.findAll();
    }
    public String deleteFromCart(Integer customercode,Integer productcode) throws Exception {
        cartDao.deleteProduct(customercode,productcode);
        return "{'code':1,'message':'record code "+productcode+" is deleted'}";
    }
    public String cancelCart(Integer code) throws Exception {
        cartDao.deleteByCustomer(code);
        return "{'code':1,'message':'record code "+code+" is deleted'}";
    }
    public String addToCart(Cart cart) throws Exception {
        return "{'code':1,'message':'record code "+cartDao.save(cart).getProductfk()+" is added'}";
    }

    @Transactional(rollbackFor=Exception.class, propagation= Propagation.REQUIRED)
    public String closeCart(Integer customercode) throws Exception {
        List<Cart> cartList=cartDao.findByCustomerfk(customercode);
        if(orderMq.sendOrder(cartList)==0) {
            cartDao.deleteByCustomer(customercode);
            return "{'code':0,'message':'your product with customer code " + customercode + " closed and send to payment part'}";
        }else return "{'code':1,'message':'Some problem is happened'}";
    }
}
