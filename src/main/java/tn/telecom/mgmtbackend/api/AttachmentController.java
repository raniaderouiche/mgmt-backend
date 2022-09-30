package tn.telecom.mgmtbackend.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.telecom.mgmtbackend.exceptions.NotFoundException;
import tn.telecom.mgmtbackend.model.Attachment;
import tn.telecom.mgmtbackend.services.AttachmentService;

import java.util.List;

@RestController
@RequestMapping("/attachment")
public class AttachmentController {
    @Autowired
    private AttachmentService attachmentService;

    @GetMapping("/")
    public List<Attachment> getAttachments(){
        return attachmentService.getAttachments();
    }

    @GetMapping("/{id}")
    public Attachment getAttachmentById(@PathVariable(name = "id") Long id) throws NotFoundException {
        return attachmentService.getAttachmentById(id);
    }

    @PostMapping("/")
    public void saveAttachment(@RequestBody Attachment attachment){
        attachmentService.saveAttachment(attachment);
    }

    @DeleteMapping("/{id}")
    public void deleteAttachment(@PathVariable(name = "id") Long id) throws NotFoundException {
        attachmentService.deleteAttachment(id);
    }

    @GetMapping("/order/{id}")
    public List<Attachment> getAttachmentByOrderId(@PathVariable(name = "id") Long id){
        return attachmentService.getAttachmentsByOrderId(id);
    }
}
