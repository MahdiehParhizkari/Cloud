package com.mahdieh.person.infrastructure.repository;
/*
  @project DDD
  @Author Mahdieh Parhizkari
  @Date 11/10/21
  @Time 5:51 AM
  Created by Intellije IDEA
  Description:
*/

import java.util.List;
import com.mahdieh.person.domain.entity.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonDao extends JpaRepository<Person, Integer>{
    Page<Person> findAll(Pageable pageable);
    List<Person> findByNationalkey(String nationalKey);
    List<Person> findByPersonpk(Integer personcode);

}