package tn.telecom.mgmtbackend.services.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.telecom.mgmtbackend.exceptions.NotFoundException;
import tn.telecom.mgmtbackend.model.Delivery;
import tn.telecom.mgmtbackend.model.ItemDelivered;
import tn.telecom.mgmtbackend.repositories.DeliveryRepository;
import tn.telecom.mgmtbackend.repositories.ItemDeliveredRepository;
import tn.telecom.mgmtbackend.services.ItemDeliveredService;

import java.util.List;

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
        item.setDelivery(delivery);
        itemDeliveredRepository.save(item);
    }

    @Override
    public void deleteItem(Long id) throws NotFoundException {
        if(itemDeliveredRepository.existsById(id)){
            itemDeliveredRepository.deleteById(id);
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
