package tn.telecom.mgmtbackend.services;

import tn.telecom.mgmtbackend.exceptions.NotFoundException;
import tn.telecom.mgmtbackend.model.PurchaseOrder;

import java.util.List;

public interface PurchaseOrderService {
    List<PurchaseOrder> getPurchaseOrders();
    void savePurchaseOrder(PurchaseOrder order);
    void deletePurchaseOrder(Long id) throws NotFoundException;

    PurchaseOrder getPurchaseOrderByID(Long id);
}
