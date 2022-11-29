package tn.telecom.mgmtbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.telecom.mgmtbackend.model.Delivery;

import java.util.List;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery,Long> {

    List<Delivery> findByPurchaseOrderId(Long id);
    List<Delivery> findByWorkOrderId(Long id);
}
