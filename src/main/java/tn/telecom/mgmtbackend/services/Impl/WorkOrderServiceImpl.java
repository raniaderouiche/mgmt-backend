package tn.telecom.mgmtbackend.services.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.telecom.mgmtbackend.exceptions.NotFoundException;
import tn.telecom.mgmtbackend.model.DefinitiveOrder;
import tn.telecom.mgmtbackend.model.ItemUsed;
import tn.telecom.mgmtbackend.model.PurchaseOrder;
import tn.telecom.mgmtbackend.model.WorkOrder;
import tn.telecom.mgmtbackend.repositories.WorkOrderRepository;
import tn.telecom.mgmtbackend.services.PurchaseOrderService;
import tn.telecom.mgmtbackend.services.WorkOrderService;

import java.time.Year;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class WorkOrderServiceImpl implements WorkOrderService {

    @Autowired
    private WorkOrderRepository workOrderRepository;

    @Autowired
    private PurchaseOrderService purchaseOrderService;


    @Override
    public List<WorkOrder> getWorkOrders() {
        return this.workOrderRepository.findAll();
    }

    @Override
    public WorkOrder getWorkOrderById(Long id) throws NotFoundException {
        if(this.workOrderRepository.findById(id).isPresent()) {
            return this.workOrderRepository.findById(id).get();
        }else{
            return null;
        }
    }

    @Override
    public void saveWorkOrder(WorkOrder workOrder, Long purchaseOrderId) {
        PurchaseOrder purchaseOrder = this.purchaseOrderService.getPurchaseOrderByID(purchaseOrderId);
        workOrder.setPurchaseOrder(purchaseOrder);
        workOrder.setOrderDate(new Date());
        this.workOrderRepository.save(workOrder);
        workOrder.setCode(workOrder.getId()+"/"+ workOrder.getPurchaseOrder().getCode());
//        Long total = 0L;
//        for (DefinitiveOrder order : workOrder.getDefinitiveOrders()){
//            for (ItemUsed item : workOrder.getPurchaseOrder().getItemsUsed()){
//                if(Objects.equals(item.getItem().getId(), order.getItem().getId())){
//                    total = total + (item.getPrice() * order.getQuantity());
//                    break;
//                }
//            }
//        }
//        workOrder.setAmount(total);
        this.workOrderRepository.save(workOrder);
    }

    @Override
    public void deleteWorkOrder(Long id) throws NotFoundException {
        if(workOrderRepository.existsById(id)){
            workOrderRepository.deleteById(id);
        }else {
            throw new NotFoundException();
        }
    }

    @Override
    public List<WorkOrder> getWorkOrdersByOrderId(Long id){
        return this.workOrderRepository.findWorkOrdersByPurchaseOrderId(id);
    }
}
