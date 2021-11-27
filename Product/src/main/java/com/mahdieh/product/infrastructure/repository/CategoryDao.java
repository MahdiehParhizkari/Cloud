package com.mahdieh.product.infrastructure.repository;
/*
  @project DDD
  @Author Mahdieh Parhizkari
  @Date 11/13/21
  @Time 10:15 AM
  Created by Intellije IDEA
  Description: JPA - Criteria
*/

import com.mahdieh.product.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CategoryDao extends JpaRepository<Category, Integer>{
    List<Category> findByCategorypk(Integer categorycode);
}
