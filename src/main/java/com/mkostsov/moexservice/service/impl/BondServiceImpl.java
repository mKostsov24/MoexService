package com.mkostsov.moexservice.service.impl;

import com.mkostsov.moexservice.dto.*;
import com.mkostsov.moexservice.exception.BondNotFoundException;
import com.mkostsov.moexservice.model.Currency;
import com.mkostsov.moexservice.model.Stock;
import com.mkostsov.moexservice.service.api.BondService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class BondServiceImpl implements BondService {
    private final BondCachingServiceImpl bondRepository;
    private final CacheManager manager;

    @Override
    public StocksDto getBondsFromMoex(TickersDto tickersDto) {
        log.info("Request for tickers {}", tickersDto.getTickers());
        List<String> tickers = new ArrayList<>(tickersDto.getTickers());

        List<BondDto> allBonds = new ArrayList<>();
        allBonds.addAll(bondRepository.getGovernmentBonds());
        allBonds.addAll(bondRepository.getCorporateBonds());
        List<BondDto> resultBonds = allBonds.stream()
                .filter(b -> tickers.contains(b.getTicker()))
                .collect(Collectors.toList());

        List<Stock> stocks = resultBonds.stream().map(b -> Stock.builder()
                .ticker(b.getTicker())
                .name(b.getName())
                .figi(b.getTicker())
                .type("Bond")
                .currency(Currency.RUB)
                .source("MOEX")
                .build()).collect(Collectors.toList());
        return StocksDto.builder().stocks(stocks).build();
    }

    @Override
    public StocksPricesDto getPricesByFigies(FigiesDto figiesDto) {
        log.info("Request for figies {}", figiesDto.getFigies());
        List<String> figies = new ArrayList<>(figiesDto.getFigies());
        List<BondDto> allBonds = new ArrayList<>();
        allBonds.addAll(bondRepository.getGovernmentBonds());
        allBonds.addAll(bondRepository.getCorporateBonds());
        figies.removeAll(allBonds.stream().map(BondDto::getTicker).collect(Collectors.toList()));
        if(!figies.isEmpty()) {
            throw new BondNotFoundException(String.format("Bonds %s not found.", figies));
        }
        List<StockPrice> prices = allBonds.stream()
                .filter(b -> figiesDto.getFigies().contains(b.getTicker()))
                .map(b -> new StockPrice(b.getTicker(), b.getPrice() * 10))
                .collect(Collectors.toList());
        return new StocksPricesDto(prices);
    }


}
