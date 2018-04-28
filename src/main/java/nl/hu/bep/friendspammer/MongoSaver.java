package nl.hu.bep.friendspammer;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoSaver {
	static final Logger logger = LoggerFactory.getLogger(MongoSaver.class);
	
	private MongoSaver() { }
	
	public static boolean saveEmail(String to, String from, String subject, String text, Boolean html) {
		String database = "friendspammer";
		
		boolean success = true;
		
		try (MongoClient mongoClient = new MongoClient("mongo") ) {
			
			MongoDatabase db = mongoClient.getDatabase( database );
			
			MongoCollection<Document> c = db.getCollection("email");
			
			Document  doc = new Document ("to", to)
			        .append("from", from)
			        .append("subject", subject)
			        .append("text", text)
			        .append("asHtml", html);
			c.insertOne(doc);
		} catch (MongoException mongoException) {
			logger.debug("Error while saving to mongo");
			
			StringWriter stringWriter = new StringWriter();
			mongoException.printStackTrace(new PrintWriter(stringWriter));
			logger.debug(stringWriter.toString());
			
			success = false;
		}
		
		return success;
	}
	
	   public static List<Email> getEmails() {
	       String database = "friendspammer";

	       MongoClient mongoClient = new MongoClient("mongo");
	       
	       MongoDatabase db = mongoClient.getDatabase(database);
	       
	       MongoCollection<Document> c = db.getCollection("email");
	       
	       Iterator<Document> it = c.find().iterator();
	       
	       List<Email> emails = new ArrayList<>();
	       
	       while(it.hasNext())
	       {
	           Document doc = it.next();
	           
	           Email email = new Email();
	           email.setTo(doc.getString("to"));
               email.setFrom(doc.getString("from"));
               email.setSubject(doc.getString("subject"));
               email.setMessageBody(doc.getString("text"));
               email.setAsHtml(doc.getBoolean("asHtml"));
               
               emails.add(email);
	       }


	       mongoClient.close();
	       return emails;
	    }

}

