package tn.telecom.mgmtbackend.services.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.telecom.mgmtbackend.exceptions.NotFoundException;
import tn.telecom.mgmtbackend.model.ItemUsed;
import tn.telecom.mgmtbackend.model.PurchaseOrder;
import tn.telecom.mgmtbackend.repositories.ItemUsedRepository;
import tn.telecom.mgmtbackend.repositories.PurchaseOrderRepository;
import tn.telecom.mgmtbackend.services.ItemUsedService;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ItemUsedServiceImpl implements ItemUsedService {

    @Autowired
    private ItemUsedRepository itemUsedRepository;
    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Override
    public List<ItemUsed> getItemsUsedByPurchaseOrder(Long id) {
        return itemUsedRepository.findItemUsedsByPurchaseOrderId(id);
    }

    @Override
    public void saveItemUsed(ItemUsed itemUsed) {
        ItemUsed oldValue = this.itemUsedRepository.findItemUsedByItemIdAndPurchaseOrderId(itemUsed.getItem().getId(),itemUsed.getPurchaseOrder().getId());
        if(oldValue!=null){
            oldValue.setQuantity(itemUsed.getQuantity() + oldValue.getQuantity());
            itemUsed = oldValue;
        }
        this.itemUsedRepository.save(itemUsed);

        changeValidationState(itemUsed.getPurchaseOrder());
    }

    @Override
    public void deleteItemUsed(Long id) throws NotFoundException {
        PurchaseOrder purchaseOrder;
        if(itemUsedRepository.findById(id).isPresent()){
            ItemUsed itemUsed = itemUsedRepository.findById(id).get();
            purchaseOrder = itemUsed.getPurchaseOrder();
            itemUsedRepository.deleteById(id);
        }else {
            throw new NotFoundException();
        }

        changeValidationState(purchaseOrder);
    }

    public void changeValidationState(PurchaseOrder purchaseOrder) {
        if(Objects.equals(purchaseOrder.getAmount(), getItemsTotal(purchaseOrder))){
            purchaseOrder.setValidationState("En attente");
        }else{
            purchaseOrder.setValidationState("Invalide");
        }
        purchaseOrderRepository.save(purchaseOrder);
        System.out.println("Saved");
    }

    @Override
    public void editItemUsed(ItemUsed itemUsed) {
        this.itemUsedRepository.save(itemUsed);
        changeValidationState(itemUsed.getPurchaseOrder());
    }

    public Double getItemsTotal(PurchaseOrder order){
        return order.getItemsUsed().stream()
                .mapToDouble(ItemUsed::getPrice)
                .reduce(0.0, Double::sum);
    }
}
