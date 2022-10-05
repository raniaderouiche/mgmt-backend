package tn.telecom.mgmtbackend.services;

import tn.telecom.mgmtbackend.exceptions.NotFoundException;
import tn.telecom.mgmtbackend.model.Market;
import tn.telecom.mgmtbackend.model.User;

import java.util.List;

public interface MarketService {

    List<Market> getMarkets();
    List<Market> getMarketsByType( String type);
    void saveMarket(Market market);
    void deleteMarket(Long id) throws NotFoundException;
    Market getMarketById(Long id);
}
