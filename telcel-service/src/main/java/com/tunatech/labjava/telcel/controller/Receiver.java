package com.tunatech.labjava.telcel.controller;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.tunatech.labjava.model.Sale;

@Component
public class Receiver {

    private static final Logger logger = LoggerFactory.getLogger(Receiver.class);

    private Random random = new Random();

    public Sale handleMessage(Sale sale) {
        logger.info("Sale received in TELCEL{}", sale);

        sale.setResponseCode("00");
        sale.setResponseMessage("APROBADA");
        sale.setApprovalCode(String.format("%06d", random.nextInt() % 1000000));

        return sale;
    }
}
