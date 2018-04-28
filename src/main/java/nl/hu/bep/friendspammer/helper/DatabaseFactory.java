package nl.hu.bep.friendspammer.helper;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoDatabase;

public class DatabaseFactory {
    private MongoDatabase database;
    
    public DatabaseFactory() {
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        
        @SuppressWarnings("resource")
        MongoClient mongoClient = new MongoClient("mongo",
                MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
        
        database = mongoClient.getDatabase("friendspammer");
    }
    
    public MongoDatabase getDatabase() {
        return database;
    }

}
