package tn.telecom.mgmtbackend.services.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.telecom.mgmtbackend.exceptions.NotFoundException;
import tn.telecom.mgmtbackend.model.WorkOrder;
import tn.telecom.mgmtbackend.repositories.WorkOrderRepository;
import tn.telecom.mgmtbackend.services.WorkOrderService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkOrderServiceImpl implements WorkOrderService {

    @Autowired
    private WorkOrderRepository workOrderRepository;


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
    public void saveWorkOrder(WorkOrder workOrder) {
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
}
