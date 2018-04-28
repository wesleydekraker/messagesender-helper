package nl.hu.bep.friendspammer;

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
	
	public static void sendEmail(String subject, String to, String messageBody, boolean asHtml) throws MessagingException {
        Session session = getMailSession();

		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress("spammer@spammer.com"));
		message.setRecipients(Message.RecipientType.TO,
			InternetAddress.parse(to));
		message.setSubject(subject);
		
		if (asHtml) {
				message.setContent(messageBody, "text/html; charset=utf-8");
		} else {
			message.setText(messageBody);	
		}
		Transport.send(message);
		
		logger.info("Message send!");

		MongoSaver.saveEmail(to, "spammer@spamer.com", subject, messageBody, asHtml);
	}

	public static void sendEmail(String subject, String[] toList, String messageBody, boolean asHtml) throws MessagingException {
		for (int index = 0; index < toList.length; index++) {
		    sendEmail(subject, toList[index], messageBody, asHtml);
		}

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
