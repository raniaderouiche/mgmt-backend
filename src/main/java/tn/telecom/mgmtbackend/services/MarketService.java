package tn.telecom.mgmtbackend.services;

import tn.telecom.mgmtbackend.exceptions.NotFoundException;
import tn.telecom.mgmtbackend.model.Market;

import java.util.List;

public interface MarketService {

    List<Market> getMarkets();
    void saveMarket(Market market);
    void deleteMarket(Long id) throws NotFoundException;
}
