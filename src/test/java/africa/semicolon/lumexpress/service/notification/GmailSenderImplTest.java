package africa.semicolon.lumexpress.service.notification;

import africa.semicolon.lumexpress.data.dto.request.EmailDetails;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class GmailSenderImplTest {
    @Autowired
    private EmailNotificationService emailNotificationService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void sendHtmlEmail() {
        String response = emailNotificationService.sendHtmlEmail(
                new EmailDetails("boyomichaelbidemi@gmail.com", "Hello Mike, This is a test email"));
        log.info("response {}", response);
        assertEquals("email sent successfully", response);
    }
}