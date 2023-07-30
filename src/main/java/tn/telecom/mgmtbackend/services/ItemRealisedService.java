package tn.telecom.mgmtbackend.services;

import tn.telecom.mgmtbackend.exceptions.NotFoundException;
import tn.telecom.mgmtbackend.model.ItemRealised;

import java.util.List;


public interface ItemRealisedService {
    List<ItemRealised> getItemsRealised();
    ItemRealised getItemRealisedById(Long id) throws NotFoundException;
    void saveItemRealised(Long attachmentId, ItemRealised itemRealised);
    void deleteItemRealised(Long id) throws NotFoundException;

    List<ItemRealised> getItemsRealisedByAttachmentID(Long id);

    void editItemRealised(Long attachmentId, ItemRealised itemRealised);
}
