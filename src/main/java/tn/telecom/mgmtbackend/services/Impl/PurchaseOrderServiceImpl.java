package tn.telecom.mgmtbackend.services.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.telecom.mgmtbackend.exceptions.NotFoundException;
import tn.telecom.mgmtbackend.model.PurchaseOrder;
import tn.telecom.mgmtbackend.repositories.PurchaseOrderRepository;
import tn.telecom.mgmtbackend.services.PurchaseOrderService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PurchaseOrderServiceImpl implements PurchaseOrderService {
    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Override
    public List<PurchaseOrder> getPurchaseOrders() {
        return purchaseOrderRepository.findAll();
    }

    @Override
    public void savePurchaseOrder(PurchaseOrder order) {
        purchaseOrderRepository.save(order);
    }

    @Override
    public void deletePurchaseOrder(Long id) throws NotFoundException {
        if(purchaseOrderRepository.existsById(id)){
            purchaseOrderRepository.deleteById(id);
        }else {
            throw new NotFoundException();
        }
    }

    @Override
    public PurchaseOrder getPurchaseOrderByID(Long id) {
        if(this.purchaseOrderRepository.findById(id).isPresent()) {
            return this.purchaseOrderRepository.findById(id).get();
        }else{
            return null;
        }
    }
}
