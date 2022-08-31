package tn.telecom.mgmtbackend.services;

import tn.telecom.mgmtbackend.exceptions.NotFoundException;
import tn.telecom.mgmtbackend.model.Item;

import java.util.List;

public interface ItemService {
    List<Item> getItems();
    void saveItem(Item item);
    void deleteItem(Long id) throws NotFoundException;
}
