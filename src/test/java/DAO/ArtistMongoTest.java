package DAO;

import Connection.MongoConn;
import Model.Artist;
import Model.Clip;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.junit.Test;
import java.util.List;

import static org.junit.Assert.*;

public class ArtistMongoTest {
    public ArtistMongo artistMongo;


    @Test
    public void insert() {
        Artist artist = new Artist(1,"testName1","1234567890",0);
        assertTrue(artistMongo.insert(artist) == true);
    }

    @Test
    public void findAll() {
        List<Artist> artists = artistMongo.findAll();
        artists.forEach(System.out::println);
    }

    @Test
    public void findById() {
        List<Artist> artists = artistMongo.findById(1);
        artists.forEach(System.out::println);
    }

    @Test
    public void findByName() {
        List<Artist> artists = artistMongo.findByName("testName1");
        artists.forEach(System.out::println);
    }


    @Test
    public void update() {
        Artist artist = new Artist(1,"testName3","1234567890",0);
        assertTrue(artistMongo.update(artist) == true);
    }

    @Test
    public void delete() {
        Artist artist = new Artist(1,"testName1","1234567890",0);
        assertTrue(artistMongo.delete(artist) == true);
    }
}
