package tn.telecom.mgmtbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.telecom.mgmtbackend.model.ItemDelivered;

import java.util.List;

@Repository
public interface ItemDeliveredRepository extends JpaRepository<ItemDelivered,Long> {

    List<ItemDelivered> findByDeliveryId(Long id);

    ItemDelivered findItemDeliveredByItemIdAndDeliveryId(Long item_id, Long att_id);
}
