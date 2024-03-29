package tn.telecom.mgmtbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.telecom.mgmtbackend.model.DefinitiveOrder;

import java.util.List;

@Repository
public interface DefinitiveOrderRepository extends JpaRepository<DefinitiveOrder, Long> {
    List<DefinitiveOrder> findDefinitiveOrdersByWorkOrderId(Long id);
    DefinitiveOrder findDefinitiveOrdersByItemIdAndWorkOrderId(Long item_id,Long order_id);
}
