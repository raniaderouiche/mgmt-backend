package tn.telecom.mgmtbackend.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.telecom.mgmtbackend.exceptions.NotFoundException;
import tn.telecom.mgmtbackend.model.Attachment;
import tn.telecom.mgmtbackend.model.ItemRealised;
import tn.telecom.mgmtbackend.services.AttachmentService;
import tn.telecom.mgmtbackend.services.ItemRealisedService;

import java.util.List;

@RestController
@RequestMapping("/itemRealised")
public class ItemRealisedController {

    @Autowired
    private ItemRealisedService itemRealisedService;

    @GetMapping("/")
    public List<ItemRealised> getItemsRealised(){
        return itemRealisedService.getItemsRealised();
    }

    @GetMapping("/{id}")
    public ItemRealised getItemRealisedById(@PathVariable(name = "id") Long id) throws NotFoundException {
        return itemRealisedService.getItemRealisedById(id);
    }

    @PostMapping("/")
    public void saveItemRealised(@RequestParam("itemId") Long itemId,
                                 @RequestParam("attachmentId")Long attachmentId,
                                 @RequestBody ItemRealised itemRealised){
        itemRealisedService.saveItemRealised(itemId,attachmentId,itemRealised);
    }

    @DeleteMapping("/{id}")
    public void deleteItemRealised(@PathVariable(name = "id") Long id) throws NotFoundException {
        itemRealisedService.deleteItemRealised(id);
    }
}
