package tn.telecom.mgmtbackend.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.telecom.mgmtbackend.exceptions.NotFoundException;
import tn.telecom.mgmtbackend.model.DefinitiveOrder;
import tn.telecom.mgmtbackend.model.ItemUsed;
import tn.telecom.mgmtbackend.model.PurchaseOrder;
import tn.telecom.mgmtbackend.services.DefinitiveOrderService;

import java.util.List;

@RestController
@RequestMapping("/definitiveOrder")
public class DefinitiveOrderController {

    @Autowired
    private DefinitiveOrderService definitiveOrderService;

    @GetMapping("/")
    public List<DefinitiveOrder> getDefinitiveOrders(){
        return definitiveOrderService.getDefinitiveOrders();
    }

    @GetMapping("/{id}")
    public DefinitiveOrder getDefinitiveOrderById(@PathVariable(name = "id") Long id) throws NotFoundException {
        return definitiveOrderService.getDefinitiveOrderById(id);
    }

    @PostMapping("/")
    public void saveDefinitiveOrder(@RequestBody DefinitiveOrder definitiveOrder,@RequestParam("workOrderID") Long workOrderID){
        definitiveOrderService.saveDefinitiveOrder(definitiveOrder,workOrderID);
    }

    @DeleteMapping("/{id}")
    public void deleteDefinitiveOrder(@PathVariable(name = "id") Long id) throws NotFoundException {
        definitiveOrderService.deleteDefinitiveOrder(id);
    }

    @GetMapping("/filter")
    public List<DefinitiveOrder> getDefinitiveOrdersByWorkOrderId(@RequestParam(name = "workorderID") Long id){
        return definitiveOrderService.getDefinitiveOrdersById(id);
    }

    @PatchMapping("/{id}")
    public void editDefinitiveOrder(@PathVariable(name = "id") Long workOrderID,@RequestBody DefinitiveOrder definitiveOrder){
        definitiveOrderService.editDefinitiveOrder(definitiveOrder,workOrderID);
    }
}
