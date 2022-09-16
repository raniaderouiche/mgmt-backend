package tn.telecom.mgmtbackend.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.telecom.mgmtbackend.exceptions.NotFoundException;
import tn.telecom.mgmtbackend.model.PurchaseOrder;
import tn.telecom.mgmtbackend.services.PurchaseOrderService;

import java.util.List;

@RestController
@RequestMapping("/purchaseOrder")
public class PurchaseOrderController {
    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @GetMapping("/")
    public List<PurchaseOrder> getPurchaseOrders(){
        return purchaseOrderService.getPurchaseOrders();
    }

    @PostMapping("/")
    public void savePurchaseOrder(@RequestBody PurchaseOrder order){
        purchaseOrderService.savePurchaseOrder(order);
    }

    @DeleteMapping("/{id}")
    public void deletePurchaseOrder(@PathVariable(name = "id") Long id) throws NotFoundException {
        purchaseOrderService.deletePurchaseOrder(id);
    }
}
