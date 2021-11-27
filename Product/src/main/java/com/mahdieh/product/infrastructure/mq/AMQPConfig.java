package com.mahdieh.product.infrastructure.mq;
/*
  @project DDD
  @Author Mahdieh Parhizkari
  @Date 11/22/21
  @Time 3:34 PM
  Created by Intellije IDEA
  Description:
*/

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AMQPConfig {
    @Value("${product.queue}") String queueName;
    @Bean Queue queue(){return new Queue(queueName);}
}
