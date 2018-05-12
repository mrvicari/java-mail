import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

/*
 * Class to represent an email Inbox
 * - user: user whom the inbox belongs to
 */
public class Inbox {

  private User user;

  public Inbox(User usr) {
    user = usr;
  }

  // Method for fetching emails from inbox
  public void read(Integer numOfEmails) {
    try {
      // Get data from user object
      final String username = user.getUsername();
      final String password = user.getPassword();

      // Use gmail and pop3 as defaults
      String host = "pop.gmail.com";
      String storeType = "pop3";

      // Set properties
      Properties props = new Properties();
      props.put("mail.pop3.host", host);
      props.put("mail.pop3.port", "995");
      props.put("mail.pop3.starttls.enable", "true");

      // Get Session and Store objects and connect with pop server
      Session session = Session.getDefaultInstance(props);

      Store store = session.getStore("pop3s");
      store.connect(host, username, password);

      // Create inbox Folder object
      Folder folder = store.getFolder("INBOX");
      folder.open(Folder.READ_ONLY);

      // Get emails from folder and store them in an array
      Message[] messages = folder.getMessages();
      System.out.println("There are " + messages.length + " messages in total");

      for (int i = (messages.length-1); i >= (messages.length - numOfEmails < 0 ? 0 : messages.length - numOfEmails); i--) {
          Message msg = messages[i];
          System.out.println("--------------------------------------------------");
          System.out.println("From: " + msg.getFrom()[0]);
          System.out.println("Subject: " + msg.getSubject());
      }

      // Close folder and store
      folder.close(true);
      store.close();
    } catch (MessagingException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
