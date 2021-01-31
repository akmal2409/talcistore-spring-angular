package tech.talci.talcistorespring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationEmail {
    private String subject;
    private String recipient;
    private String body;

    public static NotificationEmailBuilder builder() {
        return  new NotificationEmailBuilder();
    }

    public static class NotificationEmailBuilder {
        private String subject;
        private String recipient;
        private String body;

        private NotificationEmailBuilder() {}

        public NotificationEmailBuilder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public NotificationEmailBuilder recipient(String recipient) {
            this.recipient = recipient;
            return this;
        }

        public NotificationEmailBuilder body(String body) {
            this.body = body;
            return this;
        }

        public NotificationEmail build() {
            return new NotificationEmail(subject, recipient, body);
        }

    }
}
