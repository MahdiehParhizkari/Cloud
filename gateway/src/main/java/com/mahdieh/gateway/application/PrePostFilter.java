package com.mahdieh.gateway.application;
/*
  @project DDD
  @Author Mahdieh Parhizkari
  @Date 12/12/21
  @Time 1:21 PM
  Created by Intellije IDEA
  Description:
*/

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class PrePostFilter implements GlobalFilter,Ordered {
    final Logger logger =LoggerFactory.getLogger(PrePostFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("First Pre Global Filter");
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {logger.info("Last Post Global Filter");}));
    }

    @Override
    public int getOrder() {
        return -1;
    }

}
