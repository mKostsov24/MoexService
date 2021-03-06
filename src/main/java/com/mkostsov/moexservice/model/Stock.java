package com.mkostsov.moexservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder
public class Stock {
    String ticker;
    String figi;
    String name;
    String type;
    CurrencyEnum currencyEnum;
    String source;
}
