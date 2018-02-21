package DAO;

import DAO.PostgreSQL.ArtistDAO;
import domain.Artist;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ArtistDAOTest {
    @Test
    public void testFindAll(){
        ArtistDAO art = new ArtistDAO();
        List<Artist> artists = art.findAll();
        System.out.println("Test Find All method");
        artists.forEach(System.out::println);
    }



    @Test
    public void testInsert(){
        Artist artist = new Artist(1,"testName1","1234567890",0);
        ArtistDAO artistDAO = new ArtistDAO();
        assertTrue(artistDAO.insert(artist) == true);
    }

    @Test
    public void testUpdate(){
        Artist artist = new Artist(1,"testName2","1234567890",0);
        ArtistDAO artistDAO = new ArtistDAO();
        assertTrue(artistDAO.update(artist) == true);
    }

    @Test
    public void testFindById(){
        ArtistDAO art = new ArtistDAO();
        List<Artist> artists = art.findById(1);
        System.out.println("Test Find By ID method for id=1");
        artists.forEach(System.out::println);
    }

    @Test
    public void testFindByName(){
        ArtistDAO art = new ArtistDAO();
        List<Artist> artists = art.findByName("testName2");
        System.out.println("Test Find By Name method for name=testName2");
        artists.forEach(System.out::println);
    }

    @Test
    public void testDelete() {
        Artist artist = new Artist(1, "testName2", "1234567890", 0);
        ArtistDAO artistDAO = new ArtistDAO();
        assertTrue(artistDAO.delete(artist) == true);
    }

}
