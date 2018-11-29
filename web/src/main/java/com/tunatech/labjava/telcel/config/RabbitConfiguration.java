package com.tunatech.labjava.telcel.config;


import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tunatech.labjava.telcel.service.impl.DefaultTransactionHandler;

@Configuration
public class RabbitConfiguration {

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    MessageListenerAdapter listenerAdapter(DefaultTransactionHandler defaultTransactionHandler) {
        return new MessageListenerAdapter(defaultTransactionHandler, producerJackson2MessageConverter());
    }
}
