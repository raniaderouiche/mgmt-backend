package tn.telecom.mgmtbackend.services.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.telecom.mgmtbackend.exceptions.NotFoundException;
import tn.telecom.mgmtbackend.model.PurchaseOrder;
import tn.telecom.mgmtbackend.model.WorkOrder;
import tn.telecom.mgmtbackend.repositories.PurchaseOrderRepository;
import tn.telecom.mgmtbackend.repositories.WorkOrderRepository;
import tn.telecom.mgmtbackend.services.PurchaseOrderService;

import java.time.Year;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PurchaseOrderServiceImpl implements PurchaseOrderService {
    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private WorkOrderRepository workOrderRepository;

    @Override
    public List<PurchaseOrder> getPurchaseOrders() {
        return purchaseOrderRepository.findAll();
    }

    @Override
    public void savePurchaseOrder(PurchaseOrder order) {
        purchaseOrderRepository.save(order);
        order.setCode(order.getId()+"BC"+Year.now().getValue());
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

    @Override
    public PurchaseOrder getPurchaseOrderByWorkOrderID(Long id) {
        WorkOrder workOrder = new WorkOrder();
        if(workOrderRepository.findById(id).isPresent()){
            workOrder = workOrderRepository.findById(id).get();
        }
        System.out.println("ORDER = " + workOrder.getPurchaseOrder().getId());
        return workOrder.getPurchaseOrder();
    }

    @Override
    public void changeOrderValidationState(Long id,String state) {
        PurchaseOrder order;
        if(this.purchaseOrderRepository.findById(id).isPresent()) {
            order = this.purchaseOrderRepository.findById(id).get();
            order.setValidationState(state);
            System.out.println("***** STATE CHANGED ****");
            this.purchaseOrderRepository.save(order);
            System.out.println(order.getValidationState());
        }
    }
}
