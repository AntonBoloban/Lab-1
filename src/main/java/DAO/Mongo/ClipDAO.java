package DAO.Mongo;

import DAO.IDAO;
import domain.Clip;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import static Connection.MongoConn.getCollection;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

public class ClipDAO implements IDAO<Clip> {



    public static MongoCollection<Document> logsCollection = getCollection("clips");

    @Override
    public List<Clip> findAll() {
        //FindIterable<Document> coll = logsCollection.find();
        return fillList(logsCollection.find());
    }

    @Override
    public List<Clip> findById(int id) {
        //FindIterable<Document> coll = logsCollection.find(eq("id", id));
        return fillList(logsCollection.find(eq("id", id)));
    }

    @Override
    public List<Clip> findByName(String name) {

        //FindIterable<Document> coll = logsCollection.find(eq("name", name));
        return fillList(logsCollection.find(eq("name", name)));
    }

    private List<Clip> fillList(FindIterable<Document> coll){
        List<Clip> list = new ArrayList<>();
        for(Document doc : coll){
            Clip clip = new Clip();
            clip.setId(doc.getInteger("id"));
            clip.setName(doc.getString("name"));
            clip.setUrl(doc.getString("url"));
            clip.setStyle_id(doc.getInteger("style_id"));
            clip.setPerformer_id(doc.getInteger("performer_id"));
            list.add(clip);
        }
        return list;
    }

    @Override
    public boolean insert(Clip obj) {
        try {
            Document doc = new Document("id", obj.getId())
                    .append("name", obj.getName())
                    .append("url",obj.getUrl())
                    .append("performer_id",obj.getPerformer_id())
                    .append("style_id",obj.getStyle_id());
            logsCollection.insertOne(doc);
        } catch (Exception e) {
            LOGGER.log(Level.WARNING,"Exception: ",e);
            return false;
        }
        LOGGER.log(Level.INFO,"Method insert() was executed successfully!");
        return true;
    }

    @Override
    public boolean update(Clip obj) {
        try {
            Document doc = new Document("id", obj.getId())
                    .append("name", obj.getName())
                    .append("url",obj.getUrl())
                    .append("performer_id",obj.getPerformer_id())
                    .append("style_id",obj.getStyle_id());
            logsCollection.updateOne(eq("id", obj.getId()), new Document("$set", doc));;
        } catch (Exception e) {
            LOGGER.log(Level.WARNING,"Exception: ",e);
            return false;
        }
        LOGGER.log(Level.INFO,"Method update() was executed successfully!");
        return true;
    }

    @Override
    public boolean delete(Clip obj) {
        Document doc = logsCollection.find(eq("id", obj.getId())).first();
        if(!doc.isEmpty()) {
            logsCollection.deleteOne(doc);
            LOGGER.log(Level.INFO,"Method delete() was executed successfully!");
            return true;
        }
        LOGGER.log(Level.WARNING,"Clip with this id not found!");
        return false;
    }
}
