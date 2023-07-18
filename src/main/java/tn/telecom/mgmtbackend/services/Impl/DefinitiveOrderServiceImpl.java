package tn.telecom.mgmtbackend.services.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.telecom.mgmtbackend.exceptions.NotFoundException;
import tn.telecom.mgmtbackend.model.DefinitiveOrder;
import tn.telecom.mgmtbackend.model.ItemUsed;
import tn.telecom.mgmtbackend.model.WorkOrder;
import tn.telecom.mgmtbackend.repositories.DefinitiveOrderRepository;
import tn.telecom.mgmtbackend.repositories.WorkOrderRepository;
import tn.telecom.mgmtbackend.services.DefinitiveOrderService;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DefinitiveOrderServiceImpl implements DefinitiveOrderService {

    @Autowired
    private DefinitiveOrderRepository definitiveOrderRepository;

    @Autowired
    private WorkOrderRepository workOrderRepository;

    @Override
    public List<DefinitiveOrder> getDefinitiveOrders() {
        return this.definitiveOrderRepository.findAll();
    }

    @Override
    public DefinitiveOrder getDefinitiveOrderById(Long id) throws NotFoundException {

        if(this.definitiveOrderRepository.findById(id).isPresent()) {
            return this.definitiveOrderRepository.findById(id).get();
        }else{
            return null;
        }
    }

    @Override
    public void saveDefinitiveOrder(DefinitiveOrder definitiveOrder,Long workOrderID) {
        WorkOrder workOrder = this.workOrderRepository.getById(workOrderID);
        DefinitiveOrder oldOrder = this.definitiveOrderRepository.findDefinitiveOrdersByItemIdAndWorkOrderId(definitiveOrder.getItem().getId(),workOrderID);
        if(oldOrder!=null){
            oldOrder.setQuantity(definitiveOrder.getQuantity() + oldOrder.getQuantity());
            definitiveOrder = oldOrder;
        }else{
            definitiveOrder.setWorkOrder(workOrder);
        }
        this.definitiveOrderRepository.save(definitiveOrder);
        workOrder = this.workOrderRepository.getById(workOrderID);
        double total = 0L;
        for (DefinitiveOrder order : workOrder.getDefinitiveOrders()){
            for (ItemUsed item : workOrder.getPurchaseOrder().getItemsUsed()){
                if(Objects.equals(item.getItem().getId(), order.getItem().getId())){
                    total = total + (item.getPrice() * order.getQuantity());
                    break;
                }
            }
        }
        workOrder.setAmount(total);
        this.workOrderRepository.save(workOrder);
    }

    @Override
    public void deleteDefinitiveOrder(Long id) throws NotFoundException {
        if(definitiveOrderRepository.existsById(id)){
            definitiveOrderRepository.deleteById(id);
        }else {
            throw new NotFoundException();
        }
    }

    @Override
    public List<DefinitiveOrder> getDefinitiveOrdersById(Long id) {
        return definitiveOrderRepository.findDefinitiveOrdersByWorkOrderId(id);
    }
}
