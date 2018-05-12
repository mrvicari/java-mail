import java.io.Console;

public class TestEmail {
    public static void main(String [] args) {
    Console console = System.console();

    System.out.println("\nEnter login details:\n");
    String usrnm = console.readLine("[%s] ", "Username");
    char[] pswd = console.readPassword("[%s] ", "Password");

    User user = new User(usrnm, new String(pswd));

    while(true) {
      System.out.println("\n(1) Send email");
      System.out.println("(2) Read inbox");
      System.out.println("(3) Exit\n");
      String option = console.readLine("%s: ", "Select an option");
      System.out.println();

      if (option.equals("1")) {
          System.out.println("Enter content of the email:\n");
          String subject = console.readLine("[%s] ", "Subject");
          String message = console.readLine("[%s] ", "Message");

          Email email = new Email(subject, message);

          System.out.println("\nEnter receiving email address:\n");
          String receiver = console.readLine("[%s] ", "Recipient");

          email.send(user, receiver);
      } else if (option.equals("2")) {
        String numOfEmails = console.readLine("%s: ", "Number of emails to display");
        System.out.println();

        Inbox inbox = new Inbox(user);
        inbox.read(Integer.parseInt(numOfEmails));

      } else {
        break;
      }
    }
  }
}
