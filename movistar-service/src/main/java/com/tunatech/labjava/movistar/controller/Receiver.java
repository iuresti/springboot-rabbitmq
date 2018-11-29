package com.tunatech.labjava.movistar.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.tunatech.labjava.model.Sale;
import com.tunatech.labjava.movistar.service.MovistarService;

@Component
@RabbitListener(bindings = {
        @QueueBinding(
                value = @Queue(value = "movistar-queue", autoDelete = "true"),
                exchange = @Exchange(value = "sale-exchange"),
                key = "sale.MOVISTAR")
})
public class Receiver {

    private static final Logger logger = LoggerFactory.getLogger(Receiver.class);

    private MovistarService movistarService;

    public Receiver(MovistarService movistarService) {
        this.movistarService = movistarService;
    }

    @RabbitHandler
    public Sale handleMessage(Sale sale) {
        logger.info("Sale received in MOVISTAR: {}", sale);

        return movistarService.doSale(sale);
    }
}
