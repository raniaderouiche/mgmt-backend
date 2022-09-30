package tn.telecom.mgmtbackend.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import tn.telecom.mgmtbackend.exceptions.NotFoundException;
import tn.telecom.mgmtbackend.model.Market;
import tn.telecom.mgmtbackend.model.PurchaseOrder;
import tn.telecom.mgmtbackend.services.MarketService;
import tn.telecom.mgmtbackend.services.PurchaseOrderService;

import java.util.List;

@RestController
@RequestMapping("/purchaseOrder")
public class PurchaseOrderController {
    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @Autowired
    private MarketService marketService;

    @GetMapping("/")
    public List<PurchaseOrder> getPurchaseOrders(){
        return purchaseOrderService.getPurchaseOrders();
    }

    @PostMapping("/")
    public void savePurchaseOrder(@Nullable @RequestParam("marketID") Long marketID, @RequestBody PurchaseOrder order){
        Market market = marketService.getMarketById(marketID);
        order.setMarket(market);
        purchaseOrderService.savePurchaseOrder(order);
    }

    @DeleteMapping("/{id}")
    public void deletePurchaseOrder(@PathVariable(name = "id") Long id) throws NotFoundException {
        purchaseOrderService.deletePurchaseOrder(id);
    }

    @GetMapping("/{id}")
    public PurchaseOrder getPurchaseOrderByID(@PathVariable(name = "id") Long id){
        return purchaseOrderService.getPurchaseOrderByID(id);
    }
}
