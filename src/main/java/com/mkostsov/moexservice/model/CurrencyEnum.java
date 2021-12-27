package com.mkostsov.moexservice.model;

public enum CurrencyEnum {
    RUB("RUB"),
    USD("USD"),
    EUR("EUR"),
    GBP("GBP"),
    HKD("HKD"),
    CHF("CHF"),
    JPY("JPY"),
    CNY("CNY"),
    TRY("TRY");

    private String currency;

    CurrencyEnum(String currency) {
        this.currency = currency;
    }
}
