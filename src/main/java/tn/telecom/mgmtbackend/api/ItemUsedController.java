package tn.telecom.mgmtbackend.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import tn.telecom.mgmtbackend.exceptions.NotFoundException;
import tn.telecom.mgmtbackend.model.ItemUsed;
import tn.telecom.mgmtbackend.model.Market;
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

    @GetMapping("/{id}")
    public List<ItemUsed> getItemsUsedByPurchaseOrder(@PathVariable(name = "id") Long orderID){
        return itemUsedService.getItemsUsedByPurchaseOrder(orderID);
    }

    @PostMapping("/{id}")
    public void saveItemUsed(@PathVariable(name = "id") Long orderID, @RequestBody ItemUsed itemUsed){
        PurchaseOrder order = purchaseOrderService.getPurchaseOrderByID(orderID);
        itemUsed.setPurchaseOrder(order);
        itemUsedService.saveItemUsed(itemUsed);
    }

    @DeleteMapping("/{id}")
    public void deleteItemUsed(@PathVariable(name = "id") Long id) throws NotFoundException {
        itemUsedService.deleteItemUsed(id);
    }
}
