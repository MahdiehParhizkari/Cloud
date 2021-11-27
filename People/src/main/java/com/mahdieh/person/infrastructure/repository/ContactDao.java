package com.mahdieh.person.infrastructure.repository;
/*
  @project DDD
  @Author Mahdieh Parhizkari
  @Date 11/10/21
  @Time 5:50 AM
  Created by Intellije IDEA
  Description:
*/

import com.mahdieh.person.domain.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import javax.transaction.Transactional;
import java.util.UUID;

public interface ContactDao extends JpaRepository<Contact, UUID> {

    @Modifying
    @Transactional
    @Query("delete from Contact c where c.personfk = :personCode")
    int deleteByPersonfk(Integer personCode);
}
