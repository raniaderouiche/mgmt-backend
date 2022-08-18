package tn.telecom.mgmtbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.telecom.mgmtbackend.model.CustomFile;

@Repository
public interface CustomFileRepository extends JpaRepository<CustomFile, Long> {
}
