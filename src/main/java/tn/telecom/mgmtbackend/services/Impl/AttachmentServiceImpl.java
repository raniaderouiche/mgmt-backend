package tn.telecom.mgmtbackend.services.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.telecom.mgmtbackend.exceptions.NotFoundException;
import tn.telecom.mgmtbackend.model.*;
import tn.telecom.mgmtbackend.repositories.AttachmentRepository;
import tn.telecom.mgmtbackend.repositories.MarketRepository;
import tn.telecom.mgmtbackend.services.AttachmentService;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    private AttachmentRepository attachmentRepository;

    @Autowired
    private MarketRepository marketRepository;

    @Override
    public List<Attachment> getAttachments() {
        return this.attachmentRepository.findAll();
    }

    @Override
    public Attachment getAttachmentById(Long id) throws NotFoundException {
        if(this.attachmentRepository.findById(id).isPresent()) {
            return this.attachmentRepository.findById(id).get();
        }else{
            return null;
        }
    }

    @Override
    public void saveAttachment(Attachment attachment) {
        this.attachmentRepository.save(attachment);
    }

    @Override
    public void deleteAttachment(Long id) throws NotFoundException {
        if(attachmentRepository.existsById(id)){
            attachmentRepository.deleteById(id);
        }else {
            throw new NotFoundException();
        }
    }

    @Override
    public List<Attachment> getAttachmentsByOrderId(Long id){
        return this.attachmentRepository.findAttachmentsByPurchaseOrderId(id);
    }

    @Override
    public List<Attachment> getAttachmentsByWorkOrderId(Long id) {
        return this.attachmentRepository.findAttachmentsByWorkOrderId(id);
    }

    @Override
    public Double getAttachmentsSumAmount() {
        Double sum = 0.0;
        Double price = 0.0;
        List<Attachment> attachments = this.attachmentRepository.findAll();
        for(Attachment attachment : attachments){
            for(ItemRealised item : attachment.getItemsRealised()){
                if(attachment.getPurchaseOrder() != null) {
                    price = getItemRealisedPrice(item,attachment.getPurchaseOrder().getItemsUsed());
                }
                if(attachment.getWorkOrder() != null) {
                    price = getItemRealisedPrice(item,attachment.getWorkOrder().getPurchaseOrder().getItemsUsed());
                }
                sum = sum + (item.getQuantity() * price);
            }
        }
        return sum;
    }

    @Override
    public Double getAttachmentsSumAmountByMarket(Long id) {
        Double sum = 0.0;
        Double price;
        Market market = this.marketRepository.getById(id);
        for(PurchaseOrder purchaseOrder : market.getPurchaseOrders()){
            if(Objects.equals(market.getType(), "Projet")){
                List<Attachment> attachments = this.attachmentRepository.findAttachmentsByPurchaseOrderId(purchaseOrder.getId());
                for(Attachment attachment : attachments){
                    for(ItemRealised item : attachment.getItemsRealised()){
                        price = getItemRealisedPrice(item,purchaseOrder.getItemsUsed());
                        sum = sum + (item.getQuantity() * price);
                    }
                }
            }else{
                for(WorkOrder workOrder : purchaseOrder.getWorkOrders()){
                    List<Attachment> attachments = this.attachmentRepository.findAttachmentsByWorkOrderId(workOrder.getId());
                    for(Attachment attachment : attachments){
                        for(ItemRealised item : attachment.getItemsRealised()){
                            price = getItemRealisedPrice(item,purchaseOrder.getItemsUsed());
                            sum = sum + (item.getQuantity() * price);
                        }
                    }
                }
            }
        }
        return sum;
    }

    public Double getItemRealisedPrice(ItemRealised itemRealised, List<ItemUsed> itemsUsed){
        for(ItemUsed itemUsed : itemsUsed){
            if(Objects.equals(itemRealised.getItem().getId(), itemUsed.getItem().getId())){
                return itemUsed.getPrice();
            }
        }
        return 0.0;
    }


}
