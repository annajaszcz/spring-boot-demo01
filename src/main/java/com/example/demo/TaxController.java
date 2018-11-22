package com.example.demo;

import com.example.demo.taxes.GermanTaxSystem;
import com.example.demo.taxes.PolishTaxSystem;
import com.example.demo.taxes.TaxSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TaxController {

    @Autowired
    PolishTaxSystem polishTaxSystem;

    @Autowired
    GermanTaxSystem germanTaxSystem;

    @GetMapping(path = "/{country}/{type}/{value}", produces = "application/json")
    public double calculateTax(@PathVariable String country, @PathVariable Double value, @PathVariable String type) {
        switch (country) {
            case "poland" :
                return polishTaxSystem.calculateVatTax(value, TaxSystem.TypeOfProduct.valueOf(type));
            case "germany" :
                return germanTaxSystem.calculateVatTax(value, TaxSystem.TypeOfProduct.valueOf(type));
            default:
                throw new RuntimeException("Country is not supported");
        }
    }

}
