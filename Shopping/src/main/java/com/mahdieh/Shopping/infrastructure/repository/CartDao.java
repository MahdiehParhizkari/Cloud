package com.mahdieh.Shopping.infrastructure.repository;
/*
  @project DDD
  @Author Mahdieh Parhizkari
  @Date 11/17/21
  @Time 11:05 AM
  Created by Intellije IDEA
  Description:
*/

import com.mahdieh.Shopping.domain.entity.Cart;
import com.mahdieh.Shopping.domain.entity.CartPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import javax.transaction.Transactional;
import java.util.List;

public interface CartDao extends JpaRepository<Cart, CartPK> {
    List<Cart> findByCustomerfk(Integer customercode);

    @Modifying
    @Transactional
    @Query("delete from Cart c where c.customerfk = :customercode")
    Integer deleteByCustomer(Integer customercode);

    @Modifying
    @Transactional
    @Query("delete from Cart c where c.customerfk = :customercode and c.productfk = :productcode")
    Integer deleteProduct (Integer customercode, Integer productcode);
}