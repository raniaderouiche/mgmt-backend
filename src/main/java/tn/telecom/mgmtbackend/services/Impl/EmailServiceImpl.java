package tn.telecom.mgmtbackend.services.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.telecom.mgmtbackend.model.User;
import tn.telecom.mgmtbackend.repositories.UserRepository;
import tn.telecom.mgmtbackend.services.EmailService;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.ByteArrayOutputStream;
import java.util.Properties;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void sendVerificationEmail(Long id) {
        User user = userRepository.getById(id);
        System.out.println(user);
        writeVerificationEmail(user);
    }

    @Override
    public void sendInvitation(String email) {
        writeInvitationEmail(email);
    }

    private void writeVerificationEmail(User user){
        // Sender's email ID needs to be mentioned
        String sender = "bizerteMunicipality@gmail.com";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("bizerteMunicipality@gmail.com", "bulehovntrugoxjk");

            }

        });

        ByteArrayOutputStream outputStream = null;

        try {

            session.setDebug(true);
            //construct the text body part
            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText("Bonjour " + user.getFirstName() + " " + user.getLastName() + ", nous avons examiné votre demande " +
                    "et nous avons le plaisir de vous informer que votre compte a été activé" );

            //create the sender/recipient addresses
            InternetAddress iaSender = new InternetAddress(sender);
            InternetAddress iaRecipient = new InternetAddress(user.getEmail());

            //construct the mime multi part
            MimeMultipart mimeMultipart = new MimeMultipart();
            mimeMultipart.addBodyPart(textBodyPart);

            //construct the mime message
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setSender(iaSender);
            mimeMessage.setSubject("Activation de votre compte MyCPM ");
            mimeMessage.setRecipient(Message.RecipientType.TO, iaRecipient);
            mimeMessage.setContent(mimeMultipart);
            //send off the email
            Transport.send(mimeMessage);

        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private void writeInvitationEmail(String email){
        // Sender's email ID needs to be mentioned
        String sender = "bizerteMunicipality@gmail.com";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("bizerteMunicipality@gmail.com", "bulehovntrugoxjk");

            }

        });

        ByteArrayOutputStream outputStream = null;

        try {

            session.setDebug(true);
            //construct the text body part
            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText("Cher/Chère client(e),\n" +
                    "\n" +
                    "Nous avons une grande nouvelle à partager avec vous ! Notre toute nouvelle plateforme en ligne est maintenant disponible et nous sommes ravis de vous inviter à la découvrir en avant-première.\n" +
                    "\n" +
                    "Simplifiez votre vie quotidienne et profitez d'une expérience en ligne exceptionnelle grâce à notre plateforme. Gérez vos tâches plus facilement, restez connecté avec vos proches et explorez de nouvelles opportunités, le tout sur une seule plateforme conviviale.\n" +
                    "\n" +
                    "Voici ce que vous pourrez découvrir :\n" +
                    "\n" +
                    "Gestion de tâches intuitive\n" +
                    "Réseau social dynamique\n" +
                    "Ressources exclusives\n" +
                    "Confidentialité et sécurité assurées\n" +
                    "Cliquez sur le lien ci-dessous pour vous inscrire dès maintenant [Insérer le lien d'invitation].\n" +
                    "\n" +
                    "Rejoignez-nous aujourd'hui pour être parmi les premiers à profiter de cette expérience unique en ligne !\n" +
                    "\n" +
                    "Cordialement,");

            //create the sender/recipient addresses
            InternetAddress iaSender = new InternetAddress(sender);
            InternetAddress iaRecipient = new InternetAddress(email);

            //construct the mime multi part
            MimeMultipart mimeMultipart = new MimeMultipart();
            mimeMultipart.addBodyPart(textBodyPart);

            //construct the mime message
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setSender(iaSender);
            mimeMessage.setSubject("Découvrez dès maintenant notre plateforme en ligne");
            mimeMessage.setRecipient(Message.RecipientType.TO, iaRecipient);
            mimeMessage.setContent(mimeMultipart);
            //send off the email
            Transport.send(mimeMessage);

        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

}
