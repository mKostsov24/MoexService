package com.mkostsov.moexservice.dto;

import com.mkostsov.moexservice.model.Stock;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class StocksDto {
    List<Stock> stocks;
}
