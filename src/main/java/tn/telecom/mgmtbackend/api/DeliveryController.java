package tn.telecom.mgmtbackend.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.telecom.mgmtbackend.exceptions.NotFoundException;
import tn.telecom.mgmtbackend.model.Delivery;
import tn.telecom.mgmtbackend.services.DeliveryService;

import java.util.List;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @GetMapping("/")
    public List<Delivery> getDeliveries(){
        return deliveryService.getDeliveries();
    }

    @GetMapping("/{id}")
    public Delivery getDeliveryById(@PathVariable(name = "id") Long id) throws NotFoundException {
        return deliveryService.getDeliveryById(id);
    }

    @PostMapping("/")
    public void saveDelivery(@RequestBody Delivery delivery){
        deliveryService.saveDelivery(delivery);
    }

    @DeleteMapping("/{id}")
    public void deleteDelivery(@PathVariable(name = "id") Long id) throws NotFoundException {
        deliveryService.deleteDelivery(id);
    }

    @GetMapping("/purchase-order/{id}")
    public List<Delivery> getDeliveryByPurchaseOrderId(@PathVariable(name = "id") Long id){
        return deliveryService.getDeliveriesByPurchaseOrderId(id);
    }

    @GetMapping("/work-order/{id}")
    public List<Delivery> getDeliveryByWorkOrderId(@PathVariable(name = "id") Long id){
        return deliveryService.getDeliveriesByWorkOrderId(id);
    }
}
