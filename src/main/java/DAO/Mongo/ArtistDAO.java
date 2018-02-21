package DAO.Mongo;

import DAO.IDAO;
import domain.Artist;
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

public class ArtistDAO implements IDAO<Artist> {

    public static MongoCollection<Document> logsCollection = getCollection("artists");

    @Override
    public List<Artist> findAll() {
        //FindIterable<Document> coll = logsCollection.find();
        return fillList(logsCollection.find());
    }

    @Override
    public List<Artist> findById(int id) {
        //FindIterable<Document> coll = logsCollection.find(eq("id", id));
        return fillList(logsCollection.find(eq("id", id)));
    }

    @Override
    public List<Artist> findByName(String name) {

        //FindIterable<Document> coll = logsCollection.find(eq("name", name));
        return fillList(logsCollection.find(eq("name", name)));
    }

    private List<Artist> fillList(FindIterable<Document> coll){
        List<Artist> list = new ArrayList<>();
        for(Document doc : coll){
            Artist artist = new Artist();
            artist.setId(doc.getInteger("id"));
            artist.setName(doc.getString("name"));
            artist.setBiography(doc.getString("biography"));
            artist.setId_country(doc.getInteger("country_code"));
            list.add(artist);
        }
        return list;
    }

    @Override
    public boolean insert(Artist obj) {
        try {
            Document doc = new Document("id", obj.getId())
                    .append("name", obj.getName())
                    .append("biography",obj.getBiography())
                    .append("country_code",obj.getId_country());
            logsCollection.insertOne(doc);
        } catch (Exception e) {
            LOGGER.log(Level.WARNING,"Exception: ",e);
            return false;
        }
        LOGGER.log(Level.INFO,"Method insert() was executed successfully!");
        return true;
    }

    @Override
    public boolean update(Artist obj) {
        try {
            Document doc = new Document("id", obj.getId())
                    .append("name", obj.getName())
                    .append("biography",obj.getBiography())
                    .append("country_code",obj.getId_country());
            logsCollection.updateOne(eq("id", obj.getId()), new Document("$set", doc));;
        } catch (Exception e) {
            LOGGER.log(Level.WARNING,"Exception: ",e);
            return false;
        }
        LOGGER.log(Level.INFO,"Method update() was executed successfully!");
        return true;
    }

    @Override
    public boolean delete(Artist obj) {
        Document doc = logsCollection.find(eq("id", obj.getId())).first();
        if(!doc.isEmpty()) {
            logsCollection.deleteOne(doc);
            LOGGER.log(Level.INFO,"Method delete() was executed successfully!");
            return true;
        }
        LOGGER.log(Level.WARNING,"Artist with this id not found!");
        return false;
    }
}


