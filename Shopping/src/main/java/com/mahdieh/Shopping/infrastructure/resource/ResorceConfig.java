package com.mahdieh.Shopping.infrastructure.resource;/*
  @project order
  @Author Mahdieh Parhizkari
  @Date 12/5/21
  @Time 1:14 AM
  Created by Intellije IDEA
  Description: JPA - Criteria
*/


import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ResorceConfig {
    @Bean
    @LoadBalanced
    public RestTemplate restTemplateBean(){return new RestTemplate();}
}
