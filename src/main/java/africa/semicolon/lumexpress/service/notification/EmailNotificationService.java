package africa.semicolon.lumexpress.service.notification;

import africa.semicolon.lumexpress.data.dto.request.EmailDetails;

public interface EmailNotificationService {
    String sendHtmlEmail(EmailDetails emailDetails);
}
