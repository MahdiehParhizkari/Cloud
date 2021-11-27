package com.mahdieh.finance.infrastructure.mq;
/*
  @project DDD
  @Author Mahdieh Parhizkari
  @Date 11/17/21
  @Time 3:06 PM
  Created by Intellije IDEA
  Description:
*/

import com.mahdieh.finance.domain.entity.Preorder;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class OrderMq {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public List<Preorder> payInvoice(String customerCode) {
        try {
            rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
            return (new ObjectMapper()).convertValue(rabbitTemplate.receiveAndConvert(customerCode), new TypeReference<List<Preorder>>() {
            });
            //return (List<Preorder>)rabbitTemplate.receiveAndConvert(customerCode);
        } catch (Exception ex) {
            return null;
        }
    }
}
