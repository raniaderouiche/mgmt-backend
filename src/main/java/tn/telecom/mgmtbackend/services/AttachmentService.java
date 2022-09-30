package tn.telecom.mgmtbackend.services;

import tn.telecom.mgmtbackend.exceptions.NotFoundException;
import tn.telecom.mgmtbackend.model.Attachment;
import tn.telecom.mgmtbackend.model.BusinessSector;

import java.util.List;

public interface AttachmentService {
    List<Attachment> getAttachments();
    Attachment getAttachmentById(Long id) throws NotFoundException;
    void saveAttachment(Attachment attachment);
    void deleteAttachment(Long id) throws NotFoundException;

    List<Attachment> getAttachmentsByOrderId(Long id);
}
