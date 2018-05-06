package nl.hu.bep.messagesender.helper;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {
    public void sendEmail(Email email) throws MessagingException {
        email.setFrom("spammer@spammer.com");

        Session session = getMailSession();

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(email.getFrom()));

        for (String to : email.getTo()) {
            message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        }

        message.setSubject(email.getSubject());

        if (email.isAsHtml()) {
            message.setContent(email.getMessageBody(), "text/html; charset=utf-8");
        } else {
            message.setText(email.getMessageBody());
        }

        Transport.send(message);

        MongoRepository mongoSaver = new MongoRepository();
        mongoSaver.saveEmail(email);
    }

    private Session getMailSession() {
        Properties props = new Properties();
        props.put("mail.smtp.host", "mailhog");
        props.put("mail.smtp.port", "1025");
        props.put("mail.smtp.auth", "false");

        return Session.getInstance(props);
    }

}
