package tn.telecom.mgmtbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.telecom.mgmtbackend.model.ItemRealised;

import java.util.List;

@Repository
public interface ItemRealisedRepository extends JpaRepository<ItemRealised, Long> {
    List<ItemRealised> findByAttachmentId(Long id);
    ItemRealised findItemRealisedByItemIdAndAttachmentId(Long item_id, Long att_id);
}
