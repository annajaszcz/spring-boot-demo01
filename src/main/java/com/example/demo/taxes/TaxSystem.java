package com.example.demo.taxes;

public interface TaxSystem {

    double calculateVatTax(double value, TypeOfProduct type);

    enum TypeOfProduct {
        CAR, BIKE
    }

    enum Country {
        POLAND, GERMANY
    }

}
