package nl.hu.bep.messagesender.helper;

import com.mongodb.client.MongoDatabase;

interface DatabaseFactory {
    MongoDatabase getDatabase();
}
