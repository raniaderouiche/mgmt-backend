package tn.telecom.mgmtbackend.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tn.telecom.mgmtbackend.services.EmailService;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/email/{id}")
    public void sendVerificationEmail(@PathVariable(name = "id") Long id) throws Exception {
        emailService.sendVerificationEmail(id);
    }

    @PostMapping("/email/invitation")
    public void sendInvitationEmail(@RequestBody String email) throws Exception {
        emailService.sendInvitation(email);
    }
}
