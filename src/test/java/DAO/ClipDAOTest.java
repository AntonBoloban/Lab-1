package DAO;

import DAO.PostgreSQL.ClipDAO;
import domain.Clip;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

import static Connection.PostgreSQLConn.getConnection;
import static org.junit.Assert.*;

public class ClipDAOTest {


    @Test
    public void testGetConn(){
        Connection conn = null;
        try {
            conn = getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertTrue(conn != null);
    }


    @Test
    public void testInsert(){
        Clip clip = new Clip(1,"Barking","fdfdfs",1,1);
        ClipDAO clipDAO = new ClipDAO();
        assertTrue(clipDAO.insert(clip) == true);
    }

    @Test
    public void testUpdate(){
        Clip clip = new Clip(1,"BarkingU","fdfdfs",1,1);
        ClipDAO clipDAO = new ClipDAO();
        assertTrue(clipDAO.update(clip) == true);
    }

    @Test
    public void testDelete(){
        Clip clip = new Clip(8,"BarkingU","fdfdfs",1,1);
        ClipDAO clipDAO = new ClipDAO();
        assertTrue(clipDAO.delete(clip) == true);
    }

    @Test
    public void testFindAll(){
        ClipDAO clipDAO = new ClipDAO();
        List<Clip> clips = clipDAO.findAll();
        System.out.println("Test Find All method");
        clips.forEach(System.out::println);
    }


    @Test
    public void testFindById(){
        ClipDAO clipDAO = new ClipDAO();
        List<Clip> clips = clipDAO.findById(2);
        System.out.println("Test Find By ID method for id=2");
        clips.forEach(System.out::println);
    }

    @Test
    public void testFindByName(){
        ClipDAO clipDAO = new ClipDAO();
        List<Clip> clips = clipDAO.findByName("Barking");
        System.out.println("Test Find By Name method for name=Barking");
        clips.forEach(System.out::println);
    }
}