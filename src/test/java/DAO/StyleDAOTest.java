package DAO;

import DAO.PostgreSQL.StyleDAO;
import domain.Style;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

import static Connection.PostgreSQLConn.getConnection;
import static org.junit.Assert.*;

public class StyleDAOTest {



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
        Style style = new Style(3,"Art","cool");
        StyleDAO styleDAO = new StyleDAO();
        assertTrue(styleDAO.insert(style) == true);
    }

    @Test
    public void testUpdate(){
        Style style = new Style(3,"ArtS","cool");
        StyleDAO styleDAO = new StyleDAO();
        assertTrue(styleDAO.update(style) == true);
    }

    @Test
    public void testDelete(){
        Style style = new Style(8,"ArtS","cool");
        StyleDAO styleDAO = new StyleDAO();
        assertTrue(styleDAO.delete(style) == true);
    }

    @Test
    public void testFindAll(){
        StyleDAO styleDAO = new StyleDAO();
        List<Style> styles = styleDAO.findAll();
        System.out.println("Test Find All method");
        styles.forEach(System.out::println);
    }

    @Test
    public void testFindById(){
        StyleDAO styleDAO = new StyleDAO();
        List<Style> styles = styleDAO.findById(2);
        System.out.println("Test Find By ID method for id=2");
        styles.forEach(System.out::println);
    }

    @Test
    public void testFindByName(){
        StyleDAO styleDAO = new StyleDAO();
        List<Style> styles = styleDAO.findByName("Art");
        System.out.println("Test Find By Name method for name=Barking");
        styles.forEach(System.out::println);
    }
}