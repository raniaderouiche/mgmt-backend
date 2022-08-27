package tn.telecom.mgmtbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.telecom.mgmtbackend.model.Organization;

import java.util.List;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization,Long> {
    List<Organization> findByStatusIsNull();
    List<Organization> findByStatusIsTrue();
    List<Organization> findByStatusIsFalse();

}
