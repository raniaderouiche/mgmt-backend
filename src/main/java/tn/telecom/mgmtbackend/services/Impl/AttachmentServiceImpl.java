package tn.telecom.mgmtbackend.services.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.telecom.mgmtbackend.exceptions.NotFoundException;
import tn.telecom.mgmtbackend.model.Attachment;
import tn.telecom.mgmtbackend.repositories.AttachmentRepository;
import tn.telecom.mgmtbackend.services.AttachmentService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    private AttachmentRepository attachmentRepository;

    @Override
    public List<Attachment> getAttachments() {
        return this.attachmentRepository.findAll();
    }

    @Override
    public Attachment getAttachmentById(Long id) throws NotFoundException {
        if(this.attachmentRepository.findById(id).isPresent()) {
            return this.attachmentRepository.findById(id).get();
        }else{
            return null;
        }
    }

    @Override
    public void saveAttachment(Attachment attachment) {
        this.attachmentRepository.save(attachment);
    }

    @Override
    public void deleteAttachment(Long id) throws NotFoundException {
        if(attachmentRepository.existsById(id)){
            attachmentRepository.deleteById(id);
        }else {
            throw new NotFoundException();
        }
    }

    @Override
    public List<Attachment> getAttachmentsByOrderId(Long id){
        return this.attachmentRepository.findAttachmentsByPurchaseOrderId(id);
    }
}
