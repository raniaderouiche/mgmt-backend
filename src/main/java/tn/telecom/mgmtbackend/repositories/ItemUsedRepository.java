package tn.telecom.mgmtbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.telecom.mgmtbackend.model.ItemUsed;

import java.util.List;

@Repository
public interface ItemUsedRepository extends JpaRepository<ItemUsed, Long> {
    List<ItemUsed> findItemUsedsByPurchaseOrderId(Long id);
}
