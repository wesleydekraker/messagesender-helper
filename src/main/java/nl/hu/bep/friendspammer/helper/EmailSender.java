package nl.hu.bep.friendspammer.helper;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailSender {
    static final Logger logger = LoggerFactory.getLogger(EmailSender.class);

    private EmailSender() { }

    public static void sendEmail(Email email) throws MessagingException {
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

        logger.info("Message send!");

        MongoRepository mongoSaver = new MongoRepository();
        mongoSaver.saveEmail(email);
    }

    private static Session getMailSession() {
        Properties props = new Properties();
        props.put("mail.smtp.host", "mailhog");
        props.put("mail.smtp.port", "1025");
        props.put("mail.smtp.auth", "false");

        Session session = Session.getInstance(props);
        return session;
    }

}
