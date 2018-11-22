package com.example.demo.taxes;

import org.springframework.stereotype.Component;

/**
 * German tax system supports 15 and 10 percent of tax rates
 */
@Component
public class GermanTaxSystem implements TaxSystem {

    @Override
    public double calculateVatTax(double value, TypeOfProduct type) {
        if (TypeOfProduct.CAR.equals(type)) {
            return value * 0.15;
        } else if (TypeOfProduct.BIKE.equals(type)) {
            return value * 0.10;
        } else {
            throw new RuntimeException("Type of product not supported");
        }
    }

}
