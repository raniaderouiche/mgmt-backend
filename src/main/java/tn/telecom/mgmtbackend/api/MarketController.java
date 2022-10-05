package tn.telecom.mgmtbackend.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.telecom.mgmtbackend.exceptions.NotFoundException;
import tn.telecom.mgmtbackend.model.Market;
import tn.telecom.mgmtbackend.services.MarketService;

import java.util.List;

@RestController
@RequestMapping("/market")
public class MarketController {

    @Autowired
    private MarketService marketService;

    @GetMapping("/")
    public List<Market> getMarkets(){
        return marketService.getMarkets();
    }

    @GetMapping("/{id}")
    public Market getMarketById(@PathVariable(name = "id") Long id) {
        return marketService.getMarketById(id);
    }

    @GetMapping("/type/{type}")
    public List<Market> getMarketsByType(@PathVariable(name = "type") String type) {
        return marketService.getMarketsByType(type);
    }

    @PostMapping("/")
    public void saveMarket(@RequestBody Market market){
        marketService.saveMarket(market);
    }

    @DeleteMapping("/{id}")
    public void deleteMarket(@PathVariable(name = "id") Long id) throws NotFoundException {
        marketService.deleteMarket(id);
    }
}
