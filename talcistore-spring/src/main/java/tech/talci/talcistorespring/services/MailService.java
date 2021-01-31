package tech.talci.talcistorespring.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import tech.talci.talcistorespring.exceptions.TalciStoreException;
import tech.talci.talcistorespring.model.NotificationEmail;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailService {

    private final JavaMailSender mailSender;

    public void sendMail(NotificationEmail email) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("customer-service@talcistore.com");
            messageHelper.setSubject(email.getSubject());
            messageHelper.setTo(email.getRecipient());
            messageHelper.setText(email.getBody());
        };

        try {
            mailSender.send(messagePreparator);
            log.info("Email was sent to " + email.getRecipient());
        } catch (MailException e) {
            throw new TalciStoreException("Email was not sent to " + email.getRecipient());
        }
    }
}
