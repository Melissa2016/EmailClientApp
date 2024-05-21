import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;


public class Main {
    public static void main(String[] args) {
        EmailManager emailManager = new EmailManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("1. Create Email");
                System.out.println("2. Archive Email");
                System.out.println("3. Set Priority");
                System.out.println("4. Send Email");
                System.out.println("5. Display All Emails");
                System.out.println("6. Exit");
                System.out.print("Choose an option: ");
                int option = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                switch (option) {
                    case 1:
                        System.out.print("Enter recipient: ");
                        String recipient = scanner.nextLine();
                        System.out.print("Enter subject: ");
                        String subject = scanner.nextLine();
                        System.out.print("Enter body: ");
                        String body = scanner.nextLine();
                        System.out.print("Enter priority (High/Medium/Low): ");
                        String priority = scanner.nextLine();
                        System.out.print("Enter date (YYYY-MM-DD): ");
                        String date = scanner.nextLine();
                        emailManager.createEmail(recipient, subject, body, priority, date);
                        break;
                    case 2:
                        System.out.print("Enter the email index to archive: ");
                        int archiveIndex = scanner.nextInt();
                        scanner.nextLine();  // Consume newline
                        if (isValidIndex(archiveIndex, emailManager.getEmails().size())) {
                            Email emailToArchive = emailManager.getEmails().get(archiveIndex);
                            emailManager.performAction(new ArchiveEmail(emailToArchive), emailToArchive);
                        } else {
                            System.out.println("Invalid index.");
                        }
                        break;
                    case 3:
                        System.out.print("Enter the email index to set priority: ");
                        int priorityIndex = scanner.nextInt();
                        scanner.nextLine();  // Consume newline
                        if (isValidIndex(priorityIndex, emailManager.getEmails().size())) {
                            System.out.print("Enter new priority (High/Medium/Low): ");
                            String newPriority = scanner.nextLine();
                            Email emailToSetPriority = emailManager.getEmails().get(priorityIndex);
                            emailManager.performAction(new SetPriority(emailToSetPriority, newPriority), emailToSetPriority);
                        } else {
                            System.out.println("Invalid index.");
                        }
                        break;
                    case 4:
                        System.out.print("Enter the email index to send: ");
                        int sendIndex = scanner.nextInt();
                        scanner.nextLine();  // Consume newline
                        if (isValidIndex(sendIndex, emailManager.getEmails().size())) {
                            Email emailToSend = emailManager.getEmails().get(sendIndex);
                            emailManager.performAction(new SendEmail(emailToSend), emailToSend);
                        } else {
                            System.out.println("Invalid index.");
                        }
                        break;
                    case 5:
                        System.out.println("All Emails:");
                        List<Email> emails = emailManager.getEmails();
                        for (int i = 0; i < emails.size(); i++) {
                            Email email = emails.get(i);
                            System.out.println("Index: " + i + ", Subject: " + email.getSubject() + ", Priority: " + email.getPriority() + ", Date: " + email.getDate() + ", Sent: " + email.isSent() + ", Archived: " + email.isArchived());
                        }
                        break;
                    case 6:
                        scanner.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid option.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the invalid input from the scanner buffer
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }

    private static boolean isValidIndex(int index, int size) {
        return index >= 0 && index < size;
    }
}
