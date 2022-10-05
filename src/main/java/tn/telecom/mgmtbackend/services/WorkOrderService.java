package tn.telecom.mgmtbackend.services;

import tn.telecom.mgmtbackend.exceptions.NotFoundException;
import tn.telecom.mgmtbackend.model.WorkOrder;

import java.util.List;


public interface WorkOrderService {
    List<WorkOrder> getWorkOrders();
    WorkOrder getWorkOrderById(Long id) throws NotFoundException;
    void saveWorkOrder(WorkOrder workOrder, Long purchaseOrderId);
    void deleteWorkOrder(Long id) throws NotFoundException;
    List<WorkOrder> getWorkOrdersByOrderId(Long id);

}
