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
    @Value("${people.find}") private String findPath;
    public Person find(String inputValue) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request =new HttpEntity<String>(inputValue, headers);
        Person[] persons =restTemplate.postForObject(findPath, request, Person[].class);
        //String productJson =restTemplate.postForObject(findPath, request, String.class);
        return Arrays.asList(persons).get(0);
    }
}