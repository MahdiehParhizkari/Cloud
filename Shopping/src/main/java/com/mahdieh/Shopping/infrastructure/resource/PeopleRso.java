package com.mahdieh.Shopping.infrastructure.resource;
/*
  @project DDD
  @Author Mahdieh Parhizkari
  @Date 11/17/21
  @Time 1:30 PM
  Created by Intellije IDEA
  Description:
*/

import com.mahdieh.Shopping.domain.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.util.Arrays;

@Service
public class PeopleRso {
    @Autowired RestTemplate restTemplate;
    @Autowired ResorceConfig resoConfig;
    @Value("${people.who}") private String serviceName;
    public Person find(String inputValue) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request =new HttpEntity<String>(inputValue, headers);
        //Person[] persons =restTemplate.postForObject(resoConfig.getURI("people")+serviceName, request, Person[].class);
        Person[] persons =restTemplate.postForObject(serviceName, request, Person[].class);

        //String productJson =restTemplate.postForObject(findPath, request, String.class);
        return Arrays.asList(persons).get(0);
    }
}