package com.mahdieh.finance.infrastructure.repository;
/*
  @project DDD
  @Author Mahdieh Parhizkari
  @Date 11/18/21
  @Time 11:36 AM
  Created by Intellije IDEA
  Description:
*/

import com.mahdieh.finance.domain.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PaymentDao extends JpaRepository<Payment, Integer> {
    List<Payment> findByPaymentpk(Integer paymentpk);
}
