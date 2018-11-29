package com.tunatech.labjava.telcel.exception;

import com.tunatech.labjava.model.Sale;

public class SaleException extends RuntimeException {

    private Sale sale;

    public SaleException(Sale sale) {
        super(sale.getResponseCode() + "-" + sale.getResponseMessage());
        this.sale = sale;
    }

    public Sale getSale() {
        return sale;
    }
}
