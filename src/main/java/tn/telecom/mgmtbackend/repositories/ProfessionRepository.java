package tn.telecom.mgmtbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.telecom.mgmtbackend.model.Profession;

@Repository
public interface ProfessionRepository extends JpaRepository<Profession, Long> {
}
