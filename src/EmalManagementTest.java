
import org.junit.Test;
import java.util.List;
import java.util.Comparator;
import static org.junit.Assert.*;

public class EmalManagementTest {

    @Test
    public void testCreateEmail() {
        EmailManager emailManager = new EmailManager();
        emailManager.createEmail("test@example.com", "Test Subject", "Test Body", "High", "2024-05-20");
        List<Email> emails = emailManager.getEmails();
        assertEquals(1, emails.size());
    }

    @Test
    public void testPerformAction_ArchiveEmail() {
        EmailManager emailManager = new EmailManager();
        Email email = new Email("test@example.com", "Test Subject", "Test Body", "High", "2024-05-20");
        emailManager.createEmail(email.getRecipient(), email.getSubject(), email.getBody(), email.getPriority(), email.getDate());
        emailManager.performAction(new ArchiveEmail(email), email);
        assertTrue(email.isArchived());
    }

    @Test
    public void testPerformAction_SetPriority() {
        EmailManager emailManager = new EmailManager();
        Email email = new Email("test@example.com", "Test Subject", "Test Body", "High", "2024-05-20");
        emailManager.createEmail(email.getRecipient(), email.getSubject(), email.getBody(), email.getPriority(), email.getDate());
        emailManager.performAction(new SetPriority(email, "Medium"), email);
        assertEquals("Medium", email.getPriority());
    }

    @Test
    public void testSearchEmails() {
        EmailManager emailManager = new EmailManager();
        emailManager.createEmail("recipient1@example.com", "Meeting Reminder", "Don't forget the meeting tomorrow!", "High", "2024-05-15");
        emailManager.createEmail("recipient2@example.com", "Project Update", "Here's the latest update on our project progress.", "Medium", "2024-05-14");
        emailManager.createEmail("recipient3@example.com", "Vacation Planning", "Let's plan our vacation for next month!", "Low", "2024-05-13");
        List<Email> searchResults = emailManager.searchEmails("Meeting");
        assertEquals(1, searchResults.size());
    }

    @Test
    public void testSortEmails() {
        EmailManager emailManager = new EmailManager();
        emailManager.createEmail("recipient1@example.com", "Meeting Reminder", "Don't forget the meeting tomorrow!", "High", "2024-05-15");
        emailManager.createEmail("recipient2@example.com", "Project Update", "Here's the latest update on our project progress.", "Medium", "2024-05-14");
        emailManager.createEmail("recipient3@example.com", "Vacation Planning", "Let's plan our vacation for next month!", "Low", "2024-05-13");
        List<Email> sortedEmails = emailManager.sortEmails(Comparator.comparing(Email::getDate));
        assertEquals("Vacation Planning", sortedEmails.get(0).getSubject());
    }
}
