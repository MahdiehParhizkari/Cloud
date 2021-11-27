package com.mahdieh.finance.infrastructure.mq;
/*
  @project DDD
  @Author Mahdieh Parhizkari
  @Date 11/17/21
  @Time 3:06 PM
  Created by Intellije IDEA
  Description:
*/

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mahdieh.finance.domain.entity.Quantity;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ProductMq {
    @Autowired private RabbitTemplate rabbitTemplate;
    @Autowired private Binding binding;
    @Value("${product.routingkey}") String routingKey;

    public Integer sendProductQuantity(List<Quantity> quantities) {
        try {
            String quantitiesJson = (new ObjectMapper()).writeValueAsString(quantities);
            Message message = MessageBuilder.withBody(quantitiesJson.getBytes()).setContentType(MessageProperties.CONTENT_TYPE_JSON).build();
            rabbitTemplate.send(binding.getExchange(), routingKey, message);
            return 0;
        } catch (Exception ex) {
            return 1;
        }
    }
}