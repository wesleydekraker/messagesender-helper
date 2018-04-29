package nl.hu.bep.friendspammer.helper;

import com.mongodb.client.MongoDatabase;

interface DatabaseFactory {
    MongoDatabase getDatabase();
}
