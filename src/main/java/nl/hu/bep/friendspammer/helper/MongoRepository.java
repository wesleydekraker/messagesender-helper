package nl.hu.bep.friendspammer.helper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoRepository {
    private static final Logger logger = LoggerFactory.getLogger(MongoRepository.class);
    private static DatabaseFactory databaseFactory = new DatabaseFactoryImpl();
    
    public void saveEmail(Email email) {
        MongoDatabase database = databaseFactory.getDatabase();
        
        MongoCollection<Email> collection = database.getCollection("email", Email.class);
        
        collection.insertOne(email);
    }

    public List<Email> getEmails() {
        MongoDatabase database = databaseFactory.getDatabase();
        
        MongoCollection<Email> collection = database.getCollection("email", Email.class);
        
        Iterator<Email> emailIterator = collection.find().iterator();
        
        List<Email> emails = new ArrayList<Email>();
        while (emailIterator.hasNext()) {
            emails.add(emailIterator.next());
        }
        
        return emails;
    }
    
    public static void setDatabaseFactory(DatabaseFactory databaseFactory) {
        MongoRepository.databaseFactory = databaseFactory;
    }
}
