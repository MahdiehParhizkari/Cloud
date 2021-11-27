package com.mahdieh.finance.infrastructure.repository;
/*
  @project DDD
  @Author Mahdieh Parhizkari
  @Date 11/18/21
  @Time 11:35 AM
  Created by Intellije IDEA
  Description:
*/

import com.mahdieh.finance.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import javax.transaction.Transactional;

public interface OrderDao extends JpaRepository<Order, Integer> {
    @Modifying
    @Transactional
    @Query("update Order o set o.state= :state where o.orderpk= :ordercode ")
    Integer updateState(Integer ordercode, String state);
}
