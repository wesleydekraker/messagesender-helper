package nl.hu.bep.messagesender.helper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoRepository {
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
        
        List<Email> emails = new ArrayList<>();
        while (emailIterator.hasNext()) {
            emails.add(emailIterator.next());
        }
        
        return emails;
    }
    
    public static void setDatabaseFactory(DatabaseFactory databaseFactory) {
        MongoRepository.databaseFactory = databaseFactory;
    }
}
