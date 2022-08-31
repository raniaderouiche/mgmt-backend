package tn.telecom.mgmtbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.telecom.mgmtbackend.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
