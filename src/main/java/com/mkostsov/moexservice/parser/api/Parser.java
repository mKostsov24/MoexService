package com.mkostsov.moexservice.parser.api;

import com.mkostsov.moexservice.dto.BondDto;

import java.util.List;

public interface Parser {
    List<BondDto> parse(String ratesAsString);
}
