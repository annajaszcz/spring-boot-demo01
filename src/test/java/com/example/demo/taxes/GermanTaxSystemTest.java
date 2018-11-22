package com.example.demo.taxes;

import org.junit.Test;

import static com.example.demo.taxes.TaxSystem.TypeOfProduct.BIKE;
import static com.example.demo.taxes.TaxSystem.TypeOfProduct.CAR;
import static org.assertj.core.api.Assertions.assertThat;

public class GermanTaxSystemTest {

    TaxSystem taxSystem = new GermanTaxSystem();

    @Test
    public void shouldCalculate15PercentOfVatForCar() {
        // when
        TaxSystem.TypeOfProduct product = CAR;
        double value = 100;

        // give
        double vat = taxSystem.calculateVatTax(value, product);

        // then
        assertThat(vat).isEqualTo(15.00);
    }

    @Test
    public void shouldCalculate10PercentOfVatForCar() {
        // when
        TaxSystem.TypeOfProduct product = BIKE;
        double value = 100;

        // give
        double vat = taxSystem.calculateVatTax(value, product);

        // then
        assertThat(vat).isEqualTo(10.00);
    }

}
