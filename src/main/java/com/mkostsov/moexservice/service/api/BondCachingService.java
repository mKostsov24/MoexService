package com.mkostsov.moexservice.service.api;

import com.mkostsov.moexservice.dto.BondDto;

import java.util.List;

public interface BondCachingService {
    List<BondDto> getCorporateBonds();
    List<BondDto> getGovernmentBonds();
}
