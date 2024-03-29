package tn.telecom.mgmtbackend.services.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.telecom.mgmtbackend.exceptions.NotFoundException;
import tn.telecom.mgmtbackend.model.Attachment;
import tn.telecom.mgmtbackend.model.Item;
import tn.telecom.mgmtbackend.model.ItemRealised;
import tn.telecom.mgmtbackend.repositories.AttachmentRepository;
import tn.telecom.mgmtbackend.repositories.ItemRealisedRepository;
import tn.telecom.mgmtbackend.repositories.ItemRepository;
import tn.telecom.mgmtbackend.services.ItemRealisedService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemRealisedServiceImpl implements ItemRealisedService {

    @Autowired
    private ItemRealisedRepository itemRealisedRepository;

    @Autowired
    private AttachmentRepository attachmentRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List<ItemRealised> getItemsRealised() {
        return this.itemRealisedRepository.findAll();
    }

    @Override
    public ItemRealised getItemRealisedById(Long id) throws NotFoundException {
        if(this.itemRealisedRepository.findById(id).isPresent()) {
            return this.itemRealisedRepository.findById(id).get();
        }else{
            return null;
        }
    }

    @Override
    public void saveItemRealised(Long attachmentId, ItemRealised itemRealised) {
        Attachment attachment = new Attachment();
        ItemRealised oldValue = itemRealisedRepository.findItemRealisedByItemIdAndAttachmentId(itemRealised.getItem().getId(),attachmentId);
        if(oldValue!=null){
            oldValue.setQuantity(itemRealised.getQuantity() + oldValue.getQuantity());
            itemRealised = oldValue;
        }else{
            if(attachmentRepository.findById(attachmentId).isPresent()){
                attachment = attachmentRepository.findById(attachmentId).get();
            }
            itemRealised.setAttachment(attachment);
        }
        this.itemRealisedRepository.save(itemRealised);
    }

    @Override
    public void deleteItemRealised(Long id) throws NotFoundException {
        if(itemRealisedRepository.existsById(id)){
            itemRealisedRepository.deleteById(id);
        }else {
            throw new NotFoundException();
        }
    }

    @Override
    public List<ItemRealised> getItemsRealisedByAttachmentID(Long id) {
        return itemRealisedRepository.findByAttachmentId(id);
    }

    @Override
    public void editItemRealised(Long attachmentId, ItemRealised itemRealised) {
        Attachment attachment = new Attachment();
        if(attachmentRepository.findById(attachmentId).isPresent()){
            attachment = attachmentRepository.findById(attachmentId).get();
        }
        itemRealised.setAttachment(attachment);
        this.itemRealisedRepository.save(itemRealised);
    }
}
