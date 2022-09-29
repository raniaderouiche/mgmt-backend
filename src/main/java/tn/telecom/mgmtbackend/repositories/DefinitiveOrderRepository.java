package tn.telecom.mgmtbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.telecom.mgmtbackend.model.DefinitiveOrder;

@Repository
public interface DefinitiveOrderRepository extends JpaRepository<DefinitiveOrder, Long> {
}
