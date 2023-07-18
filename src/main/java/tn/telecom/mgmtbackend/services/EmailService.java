package tn.telecom.mgmtbackend.services;

import tn.telecom.mgmtbackend.model.User;

public interface EmailService {
    void sendVerificationEmail(Long id);
    void sendInvitation(String email);
}
