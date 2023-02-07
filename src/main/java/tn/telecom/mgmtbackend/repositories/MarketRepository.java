package tn.telecom.mgmtbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.telecom.mgmtbackend.model.Market;
import tn.telecom.mgmtbackend.model.Organization;
import tn.telecom.mgmtbackend.model.PurchaseOrder;

import java.util.List;

@Repository
public interface MarketRepository extends JpaRepository<Market, Long> {
    List<Market> findAllByType(String type);

    List<Market> findMarketsByTypeAndOrganization(String type,Organization org);
    List<Market> findMarketsByOrganization(Organization org);
}
