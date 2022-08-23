package tn.telecom.mgmtbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.telecom.mgmtbackend.model.BusinessSector;

@Repository
public interface BusinessSectorRepository extends JpaRepository<BusinessSector, Long> {
}
