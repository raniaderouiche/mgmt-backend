package tn.telecom.mgmtbackend.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.telecom.mgmtbackend.exceptions.NotFoundException;
import tn.telecom.mgmtbackend.model.Item;
import tn.telecom.mgmtbackend.services.ItemService;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/")
    public List<Item> getItems(){
        return itemService.getItems();
    }

    @PostMapping("/")
    public void saveItem(@RequestBody Item item){
        itemService.saveItem(item);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable(name = "id") Long id) throws NotFoundException {
        itemService.deleteItem(id);
    }
}
