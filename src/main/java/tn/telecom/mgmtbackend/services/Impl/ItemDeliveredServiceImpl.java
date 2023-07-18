package tn.telecom.mgmtbackend.services.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.telecom.mgmtbackend.exceptions.NotFoundException;
import tn.telecom.mgmtbackend.model.*;
import tn.telecom.mgmtbackend.repositories.DeliveryRepository;
import tn.telecom.mgmtbackend.repositories.ItemDeliveredRepository;
import tn.telecom.mgmtbackend.services.ItemDeliveredService;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ItemDeliveredServiceImpl implements ItemDeliveredService {

    @Autowired
    private ItemDeliveredRepository itemDeliveredRepository;

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Override
    public List<ItemDelivered> getItems() {
        return itemDeliveredRepository.findAll();
    }

    @Override
    public void saveItem(ItemDelivered item, Long deliveryId) {
        Delivery delivery = new Delivery();
        if(deliveryRepository.findById(deliveryId).isPresent()){
            delivery = deliveryRepository.findById(deliveryId).get();
        }

        /*ItemDelivered oldValue = itemDeliveredRepository.findItemDeliveredByItemIdAndDeliveryId(item.getItem().getId(),deliveryId);
        if(oldValue!=null){
            oldValue.setQuantity(item.getQuantity() + oldValue.getQuantity());
            item = oldValue;
        }else{
            if(deliveryRepository.findById(deliveryId).isPresent()){
                delivery = deliveryRepository.findById(deliveryId).get();
            }
        }*/

        item.setDelivery(delivery);
        itemDeliveredRepository.save(item);

        delivery = deliveryRepository.getById(deliveryId);
        Double total = Double.valueOf(0);
        for (ItemDelivered itemDelivered : delivery.getItemsDelivered()){
            total = total + (itemDelivered.getPrice() * itemDelivered.getQuantity());
        }
        delivery.setAmount(total);
        this.deliveryRepository.save(delivery);
    }

    @Override
    public void deleteItem(Long id) throws NotFoundException {
        if(itemDeliveredRepository.findById(id).isPresent()){
            ItemDelivered itemDelivered = itemDeliveredRepository.findById(id).get();
            Long deliveryID = itemDelivered.getDelivery().getId();
            itemDeliveredRepository.deleteById(id);
            Delivery delivery = deliveryRepository.getById(deliveryID);
            Double total = Double.valueOf(0);
            for (ItemDelivered itdel : delivery.getItemsDelivered()){
                total = total + (itdel.getPrice() * itdel.getQuantity());
            }
            delivery.setAmount(total);
            this.deliveryRepository.save(delivery);
        }else {
            throw new NotFoundException();
        }
    }

    @Override
    public ItemDelivered getItemDeliveredById(Long id) throws NotFoundException {
        if(itemDeliveredRepository.existsById(id)){
            return itemDeliveredRepository.getById(id);
        }else {
            throw new NotFoundException();
        }
    }

    @Override
    public List<ItemDelivered> getItemsDeliveredByDeliveryID(Long id) {
        return itemDeliveredRepository.findByDeliveryId(id);
    }
}
