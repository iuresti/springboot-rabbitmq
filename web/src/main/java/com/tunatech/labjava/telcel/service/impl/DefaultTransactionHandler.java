package com.tunatech.labjava.telcel.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.tunatech.labjava.model.Sale;

@Service
public class DefaultTransactionHandler {

    private static final Logger logger = LoggerFactory.getLogger(DefaultTransactionHandler.class);

    public Sale handleMessage(Sale sale) {
        logger.info("Sale received and ignored {}", sale);

        sale.setResponseCode("99");
        sale.setResponseMessage("Proveedor no disponible");

        return sale;
    }
}
