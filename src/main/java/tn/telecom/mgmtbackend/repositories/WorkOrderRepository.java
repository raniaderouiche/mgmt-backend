package tn.telecom.mgmtbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.telecom.mgmtbackend.model.Attachment;
import tn.telecom.mgmtbackend.model.WorkOrder;

import java.util.List;

@Repository
public interface WorkOrderRepository extends JpaRepository<WorkOrder, Long>{
    List<WorkOrder> findWorkOrdersByPurchaseOrderId(Long id);
}
