package tn.telecom.mgmtbackend.services.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.telecom.mgmtbackend.exceptions.NotFoundException;
import tn.telecom.mgmtbackend.model.Market;
import tn.telecom.mgmtbackend.repositories.MarketRepository;
import tn.telecom.mgmtbackend.services.MarketService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MarketServiceImpl implements MarketService {

    @Autowired
    private MarketRepository marketRepository;

    @Override
    public List<Market> getMarkets() {
        return marketRepository.findAll();
    }

    @Override
    public List<Market> getMarketsByType(String type) {
        return this.marketRepository.findAllByType(type);
    }

    @Override
    public void saveMarket(Market market) {
        marketRepository.save(market);
    }

    @Override
    public void deleteMarket(Long id) throws NotFoundException {
        if(marketRepository.existsById(id)){
            marketRepository.deleteById(id);
        }else {
            throw new NotFoundException();
        }
    }

    @Override
    public Market getMarketById(Long id) {
        if(this.marketRepository.findById(id).isPresent()) {
            return this.marketRepository.findById(id).get();
        }else{
            return null;
        }
    }

}
