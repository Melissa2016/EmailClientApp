import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


class EmailManager {
    private List<Email> emails;

    public EmailManager() {
        this.emails = new ArrayList<>();
    }

    public void createEmail(String recipient, String subject, String body, String priority, String date) {
        Email email = new Email(recipient, subject, body, priority, date);
        emails.add(email);
    }

    public void performAction(Manageable action, Email email) {
        action.performAction(email);
    }

    public List<Email> searchEmails(String keyword) {
        return emails.stream()
                .filter(email -> email.getSubject().contains(keyword) || email.getBody().contains(keyword))
                .collect(Collectors.toList());
    }

    public List<Email> sortEmails(Comparator<Email> comparator) {
        return emails.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    public List<Email> getEmails() {
        return emails;
    }
}