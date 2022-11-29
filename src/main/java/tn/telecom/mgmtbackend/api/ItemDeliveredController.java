package tn.telecom.mgmtbackend.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.telecom.mgmtbackend.exceptions.NotFoundException;
import tn.telecom.mgmtbackend.model.ItemDelivered;
import tn.telecom.mgmtbackend.services.ItemDeliveredService;

import java.util.List;

@RestController
@RequestMapping("/items-delivered")
public class ItemDeliveredController {
    @Autowired
    private ItemDeliveredService itemDeliveredService;

    @GetMapping("/")
    public List<ItemDelivered> getItemsDelivered(){
        return itemDeliveredService.getItems();
    }

    @GetMapping("/{id}")
    public ItemDelivered getItemRealisedById(@PathVariable(name = "id") Long id) throws NotFoundException {
        return itemDeliveredService.getItemDeliveredById(id);
    }

    @PostMapping("/")
    public void saveItemRealised(@RequestParam("deliveryId")Long deliveryId,
                                 @RequestBody ItemDelivered itemDelivered){
        itemDeliveredService.saveItem(itemDelivered,deliveryId);
    }

    @DeleteMapping("/{id}")
    public void deleteItemRealised(@PathVariable(name = "id") Long id) throws NotFoundException {
        itemDeliveredService.deleteItem(id);
    }

    @GetMapping("/delivery/{id}")
    public List<ItemDelivered> getItemsDeliveredByDeliveryID(@PathVariable(name = "id") Long id){
        return itemDeliveredService.getItemsDeliveredByDeliveryID(id);
    }
}
