package tn.telecom.mgmtbackend.services;

import tn.telecom.mgmtbackend.exceptions.NotFoundException;
import tn.telecom.mgmtbackend.model.Delivery;

import java.util.List;

public interface DeliveryService {

    List<Delivery> getDeliveries();
    void saveDelivery(Delivery item);
    void deleteDelivery(Long id) throws NotFoundException;
    Delivery getDeliveryById(Long id) throws NotFoundException;

    List<Delivery> getDeliveriesByPurchaseOrderId(Long id);

    List<Delivery> getDeliveriesByWorkOrderId(Long id);
}
