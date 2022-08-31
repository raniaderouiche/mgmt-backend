package tn.telecom.mgmtbackend.services.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.telecom.mgmtbackend.exceptions.NotFoundException;
import tn.telecom.mgmtbackend.model.Item;
import tn.telecom.mgmtbackend.repositories.ItemRepository;
import tn.telecom.mgmtbackend.services.ItemService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    @Override
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Override
    public void deleteItem(Long id) throws NotFoundException {
        if(itemRepository.existsById(id)){
            itemRepository.deleteById(id);
        }else {
            throw new NotFoundException();
        }
    }
}
