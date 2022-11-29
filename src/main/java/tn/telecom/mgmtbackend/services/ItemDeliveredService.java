package tn.telecom.mgmtbackend.services;

import tn.telecom.mgmtbackend.exceptions.NotFoundException;
import tn.telecom.mgmtbackend.model.ItemDelivered;

import java.util.List;

public interface ItemDeliveredService {

    List<ItemDelivered> getItems();
    void saveItem(ItemDelivered item,Long deliveryId);
    void deleteItem(Long id) throws NotFoundException;

    ItemDelivered getItemDeliveredById(Long id) throws NotFoundException;
    List<ItemDelivered> getItemsDeliveredByDeliveryID(Long id);
}
