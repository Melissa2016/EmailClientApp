


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
