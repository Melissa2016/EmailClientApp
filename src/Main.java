import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

interface Manageable {
    void performAction(Email email);
}

class Email {
    private String recipient;
    private String subject;
    private String body;
    private boolean isSent;
    private boolean isArchived;
    private String priority;
    private String date;

    public Email(String recipient, String subject, String body, String priority, String date) {
        this.recipient = recipient;
        this.subject = subject;
        this.body = body;
        this.isSent = false;
        this.isArchived = false;
        this.priority = priority;
        this.date = date;
    }

    // Getters and setters
    public String getRecipient() {
        return recipient;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    public boolean isArchived() {
        return isArchived;
    }

    public String getPriority() {
        return priority;
    }

    public String getDate() {
        return date;
    }

    public boolean isSent() {
        return isSent;
    }

    public void setSent(boolean sent) {
        isSent = sent;
    }

    public void setArchived(boolean archived) {
        isArchived = archived;
    }
}


abstract class EmailAction implements Manageable {
    protected Email email;

    public EmailAction(Email email) {
        this.email = email;
    }
}

class SendEmail extends EmailAction {
    public SendEmail(Email email) {
        super(email);
    }

    @Override
    public void performAction(Email email) {
        // Code to send email
        System.out.println("Email sent to: " + email.getRecipient());
        email.setSent(true);
    }
}

class ArchiveEmail extends EmailAction {
    public ArchiveEmail(Email email) {
        super(email);
    }

    @Override
    public void performAction(Email email) {
        email.setArchived(true);
    }
}

class SetPriority extends EmailAction {
    private String priority;

    public SetPriority(Email email, String priority) {
        super(email);
        this.priority = priority;
    }

    @Override
    public void performAction(Email email) {
        email.setPriority(priority);
    }
}

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

public class Main {
    public static void main(String[] args) {
        EmailManager emailManager = new EmailManager();

        // Create sample emails
        emailManager.createEmail("recipient1@example.com", "Meeting Reminder", "Don't forget the meeting tomorrow!", "High", "2024-05-15");
        emailManager.createEmail("recipient2@example.com", "Project Update", "Here's the latest update on our project progress.", "Medium", "2024-05-14");
        emailManager.createEmail("recipient3@example.com", "Vacation Planning", "Let's plan our vacation for next month!", "Low", "2024-05-13");

        // Perform actions
        Email emailToArchive = emailManager.getEmails().get(0);
        emailManager.performAction(new ArchiveEmail(emailToArchive), emailToArchive);

        Email emailToSetPriority = emailManager.getEmails().get(1);
        emailManager.performAction(new SetPriority(emailToSetPriority, "High"), emailToSetPriority);

        Email emailToSend = emailManager.getEmails().get(2);
        emailManager.performAction(new SendEmail(emailToSend), emailToSend);

        // Display all emails
        System.out.println("All Emails:");
        for (Email email : emailManager.getEmails()) {
            System.out.println("Subject: " + email.getSubject() + ", Priority: " + email.getPriority() + ", Date: " + email.getDate() + ", Sent: " + email.isSent() + ", Archived: " + email.isArchived());
        }
    }
}
