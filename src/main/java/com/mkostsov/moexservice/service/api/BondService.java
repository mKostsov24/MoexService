package com.mkostsov.moexservice.service.api;

import com.mkostsov.moexservice.dto.*;

public interface BondService {
    StocksDto getBondsFromMoex(TickersDto tickersDto);
    StocksPricesDto getPricesByFigies(FigiesDto figiesDto);

}
