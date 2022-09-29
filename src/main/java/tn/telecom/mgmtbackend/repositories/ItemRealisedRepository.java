package tn.telecom.mgmtbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.telecom.mgmtbackend.model.ItemRealised;

@Repository
public interface ItemRealisedRepository extends JpaRepository<ItemRealised, Long> {
}
