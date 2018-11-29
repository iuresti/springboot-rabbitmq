package com.tunatech.labjava.movistar.service.impl;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.tunatech.labjava.model.Sale;
import com.tunatech.labjava.movistar.service.MovistarService;

@Service
public class MovistarServiceImpl implements MovistarService {

    private Random random = new Random();

    @Override
    public Sale doSale(Sale sale) {
        sale.setResponseCode("00");
        sale.setResponseMessage("APROBADA");
        sale.setApprovalCode(String.format("%06d", Math.abs(random.nextInt() % 1000000)));

        return sale;
    }
}
