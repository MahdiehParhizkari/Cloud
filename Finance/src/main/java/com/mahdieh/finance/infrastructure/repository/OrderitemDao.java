package com.mahdieh.finance.infrastructure.repository;
/*
  @project DDD
  @Author Mahdieh Parhizkari
  @Date 11/18/21
  @Time 11:35 AM
  Created by Intellije IDEA
  Description:
*/

import com.mahdieh.finance.domain.entity.Orderitem;
import com.mahdieh.finance.domain.entity.OrderitemPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderitemDao extends JpaRepository<Orderitem, OrderitemPK> {
}
