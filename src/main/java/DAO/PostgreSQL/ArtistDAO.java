package DAO.PostgreSQL;
import DAO.IDAO;
import domain.Artist;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtistDAO implements IDAO<Artist> {
    public static Connection getConnection() throws Exception {
        System.out.println("-------- PostgreSQL "
                + "JDBC Connection Testing ------------");

        try {

            Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException e) {

            System.out.println("Where is your PostgreSQL JDBC Driver? "
                    + "Include in your library path!");
            e.printStackTrace();
            return null;
        }

        System.out.println("PostgreSQL JDBC Driver Registered!");

        Connection connection = null;

        try {

            connection = DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1:5432/MusicHub", "postgres",
                    "postgres");

        } catch (SQLException e) {

            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return null;

        }

        if (connection != null) {
            System.out.println("You made it, take control your database now!");
            return connection;
        } else {
            System.out.println("Failed to make connection!");
            return null;
        }
    }

    @Override
    public List<Artist> findAll() {
        List<Artist> artists = new ArrayList<Artist>();
        //Get connection with DB
        try (Connection conn = ArtistDAO.getConnection()) {
            //Prepared sql statement
            PreparedStatement st = conn.prepareStatement(
                    "Select * from artists ");

            //Execute query
            ResultSet rs = st.executeQuery();

            Artist artist = null;
            while (rs.next()) {
                artist = new Artist(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4));
                artists.add(artist);
            }
            rs.close();
            conn.close();
            return artists;
        }
        catch(Exception ex){
            return null;
        }
    }

    @Override
    public List<Artist> findById(int id) {
        List<Artist> artists  = new ArrayList<Artist>();
        //Get connection with DB
        try (Connection conn = ArtistDAO.getConnection()) {
            //Prepared sql statement
            PreparedStatement st = conn.prepareStatement(
                    "Select * from artists "+
                            "Where id = ?");

            //Set values of parameters
            st.setInt(1, id);
            //Execute query
            ResultSet rs = st.executeQuery();

            Artist artist = null;
            while (rs.next()) {
                artist = new Artist(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4));
                artists.add(artist);
            }
            rs.close();
            conn.close();
            return artists;
        }
        catch(Exception ex){
            return null;
        }
    }

    @Override
    public List<Artist> findByName(String name) {
        List<Artist> artists = new ArrayList<Artist>();
        //Get connection with DB
        try (Connection conn = ArtistDAO.getConnection()) {
            //Prepared sql statement
            PreparedStatement st = conn.prepareStatement(
                    "Select * from artists "+
                            "Where name = ?");

            //Set values of parameters
            st.setString(1, name);
            //Execute query
            ResultSet rs = st.executeQuery();

            Artist artist = null;
            while (rs.next()) {
                artist = new Artist(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4));
                artists.add(artist);
            }
            rs.close();
            conn.close();
            return artists;
        }
        catch(Exception ex){
            return null;
        }
    }

    @Override
    public boolean insert(Artist obj){
        //Get connection with DB
        try (Connection conn = ArtistDAO.getConnection()) {
            //Prepared sql statement
            PreparedStatement st = conn.prepareStatement(
                    "Insert into artists " +
                            "(name, biography, country_code) " +
                            "values ( ?, ?, ?)");

            //Set values of parameters
            //st.setInt(1, obj.getId());
            st.setString(1, obj.getName());
            st.setString(2, obj.getBiography());
            st.setInt(3, obj.getId_country());
            //Execute query
            st.executeUpdate();

            //Close conn
            conn.close();
        }
        catch(Exception ex){
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Artist obj) {
        //Get connection with DB
        try (Connection conn = ArtistDAO.getConnection()) {
            //Prepared sql statement
            PreparedStatement st = conn.prepareStatement(
                    "Update artists " +
                            "Set name = ?, biography =?, country_code =? " +
                            "Where id = ?");

            //Set values of parameters
            st.setString(1, obj.getName());
            st.setString(2, obj.getBiography());
            st.setInt(3, obj.getId_country());
            st.setInt(4, obj.getId());

            //Execute query
            st.executeUpdate();

            //Close conn
            conn.close();
        }
        catch(Exception ex){
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Artist obj) {
        //Get connection with DB
        try (Connection conn = ArtistDAO.getConnection()) {
            //Prepared sql statement
            PreparedStatement st = conn.prepareStatement(
                    "Delete from artists " +
                            "Where id = ?");

            //Set values of parameters
            st.setInt(1, obj.getId());

            //Execute query
            st.executeUpdate();

            //Close conn
            conn.close();
        }
        catch(Exception ex){
            return false;
        }
        return true;
    }
}
