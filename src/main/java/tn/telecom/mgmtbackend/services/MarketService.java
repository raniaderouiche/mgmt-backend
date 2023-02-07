package tn.telecom.mgmtbackend.services;

import tn.telecom.mgmtbackend.exceptions.NotFoundException;
import tn.telecom.mgmtbackend.model.Market;
import tn.telecom.mgmtbackend.model.User;

import java.util.List;

public interface MarketService {

    List<Market> getMarkets(String header);
    List<Market> getMarketsByType(String type,String header);
    void saveMarket(Market market,String header);
    void deleteMarket(Long id) throws NotFoundException;
    Market getMarketById(Long id);

    Market getMarketByPurchaseOrderID(Long id);
    Market getMarketByWorkOrderID(Long id);
}
