package Connection;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.logging.Level;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

public class MongoConn {

    public static MongoCollection<Document> getCollection(String coll){
        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
        MongoDatabase database = mongoClient.getDatabase("MusicHub");
        MongoCollection<Document> collection = database.getCollection(coll);
        LOGGER.log(Level.INFO,"You have connected to MongoDB successfully!");
        return collection;
    }


}
