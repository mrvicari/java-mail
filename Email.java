import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

/*
 * Class to represent an email
 * - subject: subject of the email
 * - message: content of the email
 */
public class Email {
    private String subject;
    private String message;

    public Email(String sbjct, String msg) {
        subject = sbjct;
        message = msg;
    }

    // Method for sending an email
    public void send(User user, String receiver) {
        // Get data from user object
        final String username = user.getUsername();
        final String password = user.getPassword();

        // Use gmail as default host
        String host = "smtp.gmail.com";

        // Set properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        // Get Session object with authentication
        Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

        try {
          // Create message, set headers and send.
	        Message msg = new MimeMessage(session);
	        msg.setFrom(new InternetAddress(username));
	        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver));
	        msg.setSubject(subject);
	        msg.setText(message);
	        Transport.send(msg);

	        System.out.println("\nEmail sent...");

        } catch(MessagingException e) {
            e.printStackTrace();
        }
    }
}
