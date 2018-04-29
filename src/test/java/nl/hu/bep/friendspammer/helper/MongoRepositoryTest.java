package nl.hu.bep.friendspammer.helper;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.*;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import nl.hu.bep.friendspammer.helper.Email;

public class MongoRepositoryTest {
    @SuppressWarnings("unchecked")
    @Test
    public void testGetEmails() {        
        MongoCursor<Email> mongoCursor = mock(MongoCursor.class);
        when(mongoCursor.hasNext()).thenReturn(true, false);
        when(mongoCursor.next()).thenReturn(mock(Email.class));
        
        FindIterable<Email> findIterable = mock(FindIterable.class);
        when(findIterable.iterator()).thenReturn(mongoCursor);
        
        MongoCollection<Email> mongoCollection = createMongoCollection();
        when(mongoCollection.find()).thenReturn(findIterable);
        
        MongoRepository mongoRepository = new MongoRepository();
        List<Email> emails = mongoRepository.getEmails();
        Assert.assertEquals(1, emails.size());
    }
    
    @Test
    public void testSaveEmail() {
        MongoCollection<Email> mongoCollection = createMongoCollection();
        
        MongoRepository mongoRepository = new MongoRepository();
        mongoRepository.saveEmail(mock(Email.class));
        verify(mongoCollection).insertOne(any(Email.class));
    }
    
    @SuppressWarnings("unchecked")
    private MongoCollection<Email> createMongoCollection() {
        MongoCollection<Email> mongoCollection = mock(MongoCollection.class);
        
        MongoDatabase mongoDatabase = mock(MongoDatabase.class);
        when(mongoDatabase.getCollection(any(String.class), any(Class.class))).thenReturn(mongoCollection);
        
        DatabaseFactory databaseFactory = mock(DatabaseFactory.class);
        when(databaseFactory.getDatabase()).thenReturn(mongoDatabase);
        
        MongoRepository.setDatabaseFactory(databaseFactory);
        
        return mongoCollection;
    }
}
