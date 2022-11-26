package tn.telecom.mgmtbackend.services.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.telecom.mgmtbackend.exceptions.NotFoundException;
import tn.telecom.mgmtbackend.model.DefinitiveOrder;
import tn.telecom.mgmtbackend.model.WorkOrder;
import tn.telecom.mgmtbackend.repositories.DefinitiveOrderRepository;
import tn.telecom.mgmtbackend.repositories.WorkOrderRepository;
import tn.telecom.mgmtbackend.services.DefinitiveOrderService;

import java.util.List;

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
        System.out.println("WORKID" + workOrder.getId());
        definitiveOrder.setWorkOrder(workOrder);
        this.definitiveOrderRepository.save(definitiveOrder);
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
