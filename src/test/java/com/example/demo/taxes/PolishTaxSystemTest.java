package com.example.demo.taxes;

import com.example.demo.taxes.PolishTaxSystem;
import com.example.demo.taxes.TaxSystem;
import org.junit.Test;

import static com.example.demo.taxes.TaxSystem.TypeOfProduct.BIKE;
import static com.example.demo.taxes.TaxSystem.TypeOfProduct.CAR;
import static org.assertj.core.api.Assertions.assertThat;

public class PolishTaxSystemTest {

    TaxSystem taxSystem = new PolishTaxSystem();

    @Test
    public void shouldCalculate23PercentOfVatForCar() {
        // when
        TaxSystem.TypeOfProduct product = CAR;
        double value = 100;

        // give
        double vat = taxSystem.calculateVatTax(value, product);

        // then
        assertThat(vat).isEqualTo(23.00);
    }

    @Test
    public void shouldCalculate18PercentOfVatForCar() {
        // when
        TaxSystem.TypeOfProduct product = BIKE;
        double value = 100;

        // give
        double vat = taxSystem.calculateVatTax(value, product);

        // then
        assertThat(vat).isEqualTo(18.00);
    }

}
