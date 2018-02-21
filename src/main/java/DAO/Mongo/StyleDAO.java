package DAO.Mongo;

import DAO.IDAO;
import domain.Style;
import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import static com.mongodb.client.model.Filters.eq;
import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

public class StyleDAO implements IDAO<Style> {



    public static MongoCollection<Document> logsCollection;

    public static MongoCollection<Document> getCollection(){
        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
        MongoDatabase database = mongoClient.getDatabase("MusicHub");
        MongoCollection<Document> collection = database.getCollection("styles");
        logsCollection = collection;
        return collection;
    }
    @Override
    public List<Style> findAll() {
        FindIterable<Document> coll = logsCollection.find();
        return fillList(logsCollection.find());
    }

    @Override
    public List<Style> findById(int id) {
        FindIterable<Document> coll = logsCollection.find(eq("id", id));
        return fillList(logsCollection.find(eq("id", id)));
    }

    @Override
    public List<Style> findByName(String name) {

        FindIterable<Document> coll = logsCollection.find(eq("name", name));
        return fillList(logsCollection.find(eq("name", name)));
    }

    private List<Style> fillList(FindIterable<Document> coll){
        List<Style> list = new ArrayList<>();
        for(Document doc : coll){
            Style style = new Style();
            style.setId(doc.getInteger("id"));
            style.setName(doc.getString("name"));
            style.setDescription(doc.getString("description"));
            list.add(style);
        }
        return list;
    }

    @Override
    public boolean insert(Style obj) {
        try {
            Document doc = new Document("id", obj.getId())
                    .append("name", obj.getName())
                    .append("description",obj.getDescription());
            logsCollection.insertOne(doc);
        } catch (Exception e) {
            LOGGER.log(Level.WARNING,"Exception: ",e);
            return false;
        }
        LOGGER.log(Level.INFO,"Method insert() was executed successfully!");
        return true;
    }

    @Override
    public boolean update(Style obj) {
        try {
            Document doc = new Document("id", obj.getId())
                    .append("name", obj.getName())
                    .append("description",obj.getDescription());
            logsCollection.updateOne(eq("id", obj.getId()), new Document("$set", doc));;
        } catch (Exception e) {
            LOGGER.log(Level.WARNING,"Exception: ",e);
            return false;
        }
        LOGGER.log(Level.INFO,"Method update() was executed successfully!");
        return true;
    }

    @Override
    public boolean delete(Style obj) {
        Document doc = logsCollection.find(eq("id", obj.getId())).first();
        if(!doc.isEmpty()) {
            logsCollection.deleteOne(doc);
            LOGGER.log(Level.INFO,"Method delete() was executed successfully!");
            return true;
        }
        LOGGER.log(Level.WARNING,"Style with this id not found!");
        return false;
    }
}
