package com.example.demo.taxes;

import org.springframework.stereotype.Component;

/**
 * Polish tax system supports 23 and 18 percent of tax rates
 */
@Component
public class PolishTaxSystem implements TaxSystem {

    @Override
    public double calculateVatTax(double value, TypeOfProduct type) {
        if (TypeOfProduct.CAR.equals(type)) {
            return value * 0.23;
        } else if (TypeOfProduct.BIKE.equals(type)) {
            return value * 0.18;
        } else {
            throw new RuntimeException("Type of product not supported");
        }
    }

}
