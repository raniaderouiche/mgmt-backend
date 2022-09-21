package tn.telecom.mgmtbackend.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import tn.telecom.mgmtbackend.model.ItemUsed;
import tn.telecom.mgmtbackend.model.PurchaseOrder;
import tn.telecom.mgmtbackend.services.ItemUsedService;
import tn.telecom.mgmtbackend.services.PurchaseOrderService;

import java.util.List;

@RestController
@RequestMapping("/itemUsed")
public class ItemUsedController {

    @Autowired
    private ItemUsedService itemUsedService;

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @GetMapping("/")
    public List<ItemUsed> getItemUsedByPurchaseOrder(@Nullable @RequestParam("orderID") Long orderID){
        return itemUsedService.getItemsUsedByPurchaseOrder(orderID);
    }

    @PostMapping("/")
    public void saveItemUsed(@Nullable @RequestParam("orderID") Long orderID, @RequestBody ItemUsed itemUsed){
        PurchaseOrder order = purchaseOrderService.getPurchaseOrderByID(orderID);
        itemUsed.setPurchaseOrder(order);
        itemUsedService.saveItemUsed(itemUsed);
    }
}
