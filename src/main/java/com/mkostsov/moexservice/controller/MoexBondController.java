package com.mkostsov.moexservice.controller;

import com.mkostsov.moexservice.dto.FigiesDto;
import com.mkostsov.moexservice.dto.StocksDto;
import com.mkostsov.moexservice.dto.StocksPricesDto;
import com.mkostsov.moexservice.dto.TickersDto;
import com.mkostsov.moexservice.service.api.BondService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bonds")
public class MoexBondController {
    private  final BondService bondService;

    @PostMapping("/getBondsByTickers")
    public StocksDto getBondsFromMoex(@RequestBody TickersDto tickersDto) {
        return bondService.getBondsFromMoex(tickersDto);
    }

    @PostMapping("/prices")
    public StocksPricesDto getPricesByFigies(@RequestBody FigiesDto figiesDto) {
        return bondService.getPricesByFigies(figiesDto);
    }
}
