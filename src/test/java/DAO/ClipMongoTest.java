package DAO;

import Connection.MongoConn;
import DAO.Mongo.ClipDAO;
import domain.Clip;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.junit.Test;
import java.util.List;

import static org.junit.Assert.*;

public class ClipMongoTest {

    public ClipDAO clipMongo;

    public ClipMongoTest(){
        clipMongo = new ClipDAO();
    }

    @Test
    public void getCollection() {
        MongoCollection<Document> coll = MongoConn.getCollection("clips");
        assertTrue((coll != null ? true : false) == true);
    }

    @Test
    public void findAll() {
        List<Clip> clips = clipMongo.findAll();
        clips.forEach(System.out::println);
    }



    @Test
    public void findById() {
        List<Clip> clips = clipMongo.findById(3);
        clips.forEach(System.out::println);
    }

    @Test
    public void findByName() {
        List<Clip> clips = clipMongo.findByName("Barking");
        clips.forEach(System.out::println);
    }

    @Test
    public void insert() {
        Clip clip = new Clip(3,"Jazz","fdfdfs",1,1);
        assertTrue(clipMongo.insert(clip) == true);
    }

    @Test
    public void update() {
        Clip clip = new Clip(1,"BarkingU","fdfdfs",1,1);
        assertTrue(clipMongo.update(clip) == true);
    }

    @Test
    public void delete() {
        Clip clip = new Clip(3,"BarkingU","fdfdfs",1,1);
        assertTrue(clipMongo.delete(clip) == true);
    }
}