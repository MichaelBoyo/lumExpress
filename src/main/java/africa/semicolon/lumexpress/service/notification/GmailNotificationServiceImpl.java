package africa.semicolon.lumexpress.service.notification;

import africa.semicolon.lumexpress.data.dto.request.EmailDetails;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class GmailNotificationServiceImpl implements EmailNotificationService {
    private final JavaMailSender javaMailSender;

    @Override
    public String sendHtmlEmail(EmailDetails emailDetails) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        try {
            mimeMessageHelper.setFrom("no-reply@gmail.luminary.com.ng");
            mimeMessageHelper.setTo(emailDetails.userEmail());
            mimeMessageHelper.setSubject("Luminary Express");
            mimeMessageHelper.setText(emailDetails.mailContent(), true);
            javaMailSender.send(mimeMessage);
            return "email sent successfully";
        }catch (MessagingException e) {
            e.printStackTrace();
            return "email not sent";
        }
    }

}
