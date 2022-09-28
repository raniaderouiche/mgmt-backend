package tn.telecom.mgmtbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.telecom.mgmtbackend.model.Organization;
import tn.telecom.mgmtbackend.model.User;

import java.util.List;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization,Long> {
    List<Organization> findByStatusIsNull();
    List<Organization> findByStatusIsTrue();
    List<Organization> findByStatusIsFalse();
    boolean existsByAdminOrg(User adminOrg);
    Organization findByAdminOrg(User adminOrg);

}
