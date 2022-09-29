package tn.telecom.mgmtbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.telecom.mgmtbackend.model.Attachment;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
}