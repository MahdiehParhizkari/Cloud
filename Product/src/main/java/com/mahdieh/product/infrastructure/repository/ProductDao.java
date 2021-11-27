package com.mahdieh.product.infrastructure.repository;
/*
  @project DDD
  @Author Mahdieh Parhizkari
  @Date 11/13/21
  @Time 10:15 AM
  Created by Intellije IDEA
  Description:
*/

import com.mahdieh.product.domain.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductDao extends JpaRepository<Product, Integer> {
    Page<Product> findAll(Pageable pageable);
    List<Product> findByProductpk(Integer productcode);
    Page<Product> findByActive(Pageable pageable, Boolean isactive);
    List<Product> findByProductpkIn(List<Integer> productkeys);
}
