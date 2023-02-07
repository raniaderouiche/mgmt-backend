package tn.telecom.mgmtbackend.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.telecom.mgmtbackend.exceptions.NotFoundException;
import tn.telecom.mgmtbackend.model.Market;
import tn.telecom.mgmtbackend.model.User;
import tn.telecom.mgmtbackend.services.MarketService;
import tn.telecom.mgmtbackend.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/market")
public class MarketController {

    @Autowired
    private MarketService marketService;

    @GetMapping("/")
    public List<Market> getMarkets(HttpServletRequest request){
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        return marketService.getMarkets(authorizationHeader);
    }

    @GetMapping("/{id}")
    public Market getMarketById(@PathVariable(name = "id") Long id) {
        return marketService.getMarketById(id);
    }

    @GetMapping("/type/{type}")
    public List<Market> getMarketsByType(@PathVariable(name = "type") String type, HttpServletRequest request) {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        return marketService.getMarketsByType(type,authorizationHeader);
    }

    @PostMapping("/")
    public void saveMarket(@RequestBody Market market, HttpServletRequest request){
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        marketService.saveMarket(market,authorizationHeader);
    }

    @DeleteMapping("/{id}")
    public void deleteMarket(@PathVariable(name = "id") Long id) throws NotFoundException {
        marketService.deleteMarket(id);
    }

    @GetMapping("/purchase-order/{id}")
    public Market getMarketByPurchaseOrderId(@PathVariable(name = "id") Long id) {
        return marketService.getMarketByPurchaseOrderID(id);
    }

    @GetMapping("/work-order/{id}")
    public Market getMarketByWorkOrderId(@PathVariable(name = "id") Long id) {
        return marketService.getMarketByWorkOrderID(id);
    }
}
