package tn.telecom.mgmtbackend.services;

import tn.telecom.mgmtbackend.exceptions.NotFoundException;
import tn.telecom.mgmtbackend.model.ItemUsed;

import java.util.List;

public interface ItemUsedService {
    List<ItemUsed> getItemsUsedByPurchaseOrder(Long id);
    void saveItemUsed(ItemUsed itemUsed);
    void deleteItemUsed(Long id) throws NotFoundException;
}
