package com.tunatech.labjava.telcel.service.impl;

import static com.tunatech.labjava.config.Constants.EXCHANGE_NAME;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import com.tunatech.labjava.model.Sale;
import com.tunatech.labjava.telcel.exception.SaleException;
import com.tunatech.labjava.telcel.service.SaleService;

@Service
public class RabbitSaleService implements SaleService {

    private RabbitTemplate rabbitTemplate;

    public RabbitSaleService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public Sale doSale(Sale sale) {

        Sale response = rabbitTemplate.convertSendAndReceiveAsType(EXCHANGE_NAME, "sale." + sale.getVendor(), sale, ParameterizedTypeReference.forType(Sale.class));

        if (response == null) {
            sale.setResponseCode("99");
            sale.setResponseMessage("Proveedor no disponible");
            throw new SaleException(sale);
        }

        if (!response.getResponseCode().equals("00")) {
            throw new SaleException(response);
        }

        return response;
    }
}
