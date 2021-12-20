package com.mkostsov.moexservice.service.impl;

import com.mkostsov.moexservice.dto.BondDto;
import com.mkostsov.moexservice.exception.LimitRequestsException;
import com.mkostsov.moexservice.moexclient.CorporateBondsClient;
import com.mkostsov.moexservice.moexclient.GovernmentBondsClient;
import com.mkostsov.moexservice.parser.api.Parser;
import com.mkostsov.moexservice.service.api.BondCachingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BondCachingServiceImpl implements BondCachingService {

    private final CorporateBondsClient corporateBondsClient;
    private final GovernmentBondsClient governmentBondsClient;
    private final Parser bondsParser;


    @Cacheable(value = "corps")
    public List<BondDto> getCorporateBonds() {
        log.info("Getting corporate bonds from Moex");
        String xmlFromMoex = corporateBondsClient.getBondsFromMoex();
        List<BondDto> bonds = bondsParser.parse(xmlFromMoex);
        if(bonds.isEmpty()) {
            log.error("Moex isn't answering for getting corporate bonds.");
            throw new LimitRequestsException("Moex isn't answering for getting corporate bonds.");
        }
        return bonds;
    }

    @Cacheable(value = "govs")
    public List<BondDto> getGovernmentBonds() {
        log.info("Getting government bonds from Moex");
        String xmlFromMoex = governmentBondsClient.getBondsFromMoex();

        List<BondDto> bonds = bondsParser.parse(xmlFromMoex);
        if(bonds.isEmpty()) {
            log.error("Moex isn't answering for getting government bonds.");
            throw new LimitRequestsException("Moex isn't answering for getting government bonds.");
        }
        return bonds;
    }

}
