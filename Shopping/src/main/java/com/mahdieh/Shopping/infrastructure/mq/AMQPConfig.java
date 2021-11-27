package com.mahdieh.Shopping.infrastructure.mq;
/*
  @project DDD
  @Author Mahdieh Parhizkari
  @Date 11/17/21
  @Time 11:54 AM
  Created by Intellije IDEA
  Description:
*/

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class AMQPConfig {
    @Value("${rabbitmq.exchange}") String exchangeName;
    //String exchangeName="orderEx";

    @Bean public TopicExchange getExchange(){return new TopicExchange(exchangeName,Boolean.TRUE,Boolean.FALSE);}

    public static Map<String, Object> getArguments() {
        Map<String, Object> arguments=new HashMap<>();
        arguments.put("x-message-ttl",360000);//100*60*60=1hour
        arguments.put("x-expires", 60480000);//idle Queue : 100*60*60*24*7=1Week
        arguments.put("x-max-length", 1000);//message
        arguments.put("x-max-length-bytes", 3145728);//1024*1024*3=3MByte
        arguments.put("x-queue-mode", "lazy");//Saved message on HDD
        return arguments;
    }
}
