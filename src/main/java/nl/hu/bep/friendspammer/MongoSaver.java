package nl.hu.bep.friendspammer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoSaver {
    private static final Logger logger = LoggerFactory.getLogger(MongoSaver.class);
    private static final DatabaseFactory databaseFactory = new DatabaseFactory();

    private MongoSaver() { }

    public static void saveEmail(Email email) {
        MongoDatabase database = databaseFactory.getDatabase();
        
        MongoCollection<Email> collection = database.getCollection("email", Email.class);
        
        collection.insertOne(email);
    }

    public static List<Email> getEmails() {
        MongoDatabase database = databaseFactory.getDatabase();
        
        MongoCollection<Email> collection = database.getCollection("email", Email.class);
        
        Iterator<Email> emailIterator = collection.find().iterator();
        
        List<Email> emails = new ArrayList<Email>();
        while (emailIterator.hasNext()) {
            emails.add(emailIterator.next());
        }
        
        return emails;
    }
}
