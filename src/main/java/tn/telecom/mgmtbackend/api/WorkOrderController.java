package tn.telecom.mgmtbackend.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.telecom.mgmtbackend.exceptions.NotFoundException;
import tn.telecom.mgmtbackend.model.Attachment;
import tn.telecom.mgmtbackend.model.WorkOrder;
import tn.telecom.mgmtbackend.services.AttachmentService;
import tn.telecom.mgmtbackend.services.WorkOrderService;

import java.util.List;

@RestController
@RequestMapping("/workOrder")
public class WorkOrderController {
    @Autowired
    private WorkOrderService workOrderService;

    @GetMapping("/")
    public List<WorkOrder> getWorkOrders(){
        return workOrderService.getWorkOrders();
    }

    @GetMapping("/{id}")
    public WorkOrder getWorkOrderById(@PathVariable(name = "id") Long id) throws NotFoundException {
        return workOrderService.getWorkOrderById(id);
    }

    @PostMapping("/")
    public void saveWorkOrder(@RequestBody WorkOrder workOrder){
        workOrderService.saveWorkOrder(workOrder);
    }

    @DeleteMapping("/{id}")
    public void deleteWorkOrder(@PathVariable(name = "id") Long id) throws NotFoundException {
        workOrderService.deleteWorkOrder(id);
    }
}
