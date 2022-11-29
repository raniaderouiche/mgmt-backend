package tn.telecom.mgmtbackend.services.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.telecom.mgmtbackend.exceptions.NotFoundException;
import tn.telecom.mgmtbackend.model.Delivery;
import tn.telecom.mgmtbackend.repositories.DeliveryRepository;
import tn.telecom.mgmtbackend.services.DeliveryService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Override
    public List<Delivery> getDeliveries() {
        return deliveryRepository.findAll();
    }

    @Override
    public void saveDelivery(Delivery item) {
        deliveryRepository.save(item);
    }

    @Override
    public void deleteDelivery(Long id) throws NotFoundException {
        if(deliveryRepository.existsById(id)){
            deliveryRepository.deleteById(id);
        }else {
            throw new NotFoundException();
        }
    }

    @Override
    public Delivery getDeliveryById(Long id) throws NotFoundException {

        if(deliveryRepository.existsById(id)){
            return deliveryRepository.getById(id);
        }else {
            throw new NotFoundException();
        }
    }

    @Override
    public List<Delivery> getDeliveriesByPurchaseOrderId(Long id) {
        return deliveryRepository.findByPurchaseOrderId(id);
    }

    @Override
    public List<Delivery> getDeliveriesByWorkOrderId(Long id) {
        return deliveryRepository.findByWorkOrderId(id);
    }
}
