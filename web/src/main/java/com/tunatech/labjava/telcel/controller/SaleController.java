package com.tunatech.labjava.telcel.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tunatech.labjava.model.Sale;
import com.tunatech.labjava.telcel.exception.SaleException;
import com.tunatech.labjava.telcel.service.SaleService;

@RestController
public class SaleController {

    private static final Logger logger = LoggerFactory.getLogger(SaleController.class);

    private SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping("/sale")
    public ResponseEntity<Sale> doSale(Sale sale) {

        logger.info("sale request received: {}", sale);

        sale = saleService.doSale(sale);

        return new ResponseEntity<>(sale, HttpStatus.OK);
    }

    @ExceptionHandler(SaleException.class)
    public ResponseEntity<Sale> handleError(SaleException ex) {
        return new ResponseEntity<>(ex.getSale(), HttpStatus.CONFLICT);
    }

}
