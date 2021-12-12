package com.mahdieh.finance.infrastructure.resource;
/*
  @project DDD
  @Author Mahdieh Parhizkari
  @Date 12/4/21
  @Time 8:40 PM
  Created by Intellije IDEA
  Description: OpenFeign
*/

import com.mahdieh.finance.domain.entity.Person;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class PeopleResFB implements PeopleRes{

    @Override
    public List<Person> find(String inputValue) {
        System.out.println("who service Circuit is open");
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person(0,0,0,"0000000000","People microservice is down","People/who doesn't response. try it later"));
        return personList;
    }
}
