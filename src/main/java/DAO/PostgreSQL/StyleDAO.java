package DAO.PostgreSQL;
import DAO.IDAO;
import domain.Style;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import static Connection.PostgreSQLConn.getConnection;
import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;


public class StyleDAO implements IDAO<Style> {

    @Override
    public List<Style> findAll() {
        List<Style> styles = new ArrayList<Style>();
        //Get connection with DB
        try (Connection conn = getConnection()) {
            //Prepared sql statement
            PreparedStatement st = conn.prepareStatement(
                    "Select * from styles ");


            //Execute query
            ResultSet rs = st.executeQuery();

            Style style = null;
            while (rs.next()) {
                style = new Style(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3));
                styles.add(style);
            }
            rs.close();
            conn.close();
            LOGGER.log(Level.INFO,"Method findAll() was executed successfully!");
            return styles;
        }
        catch(SQLException sqlex){
            LOGGER.log(Level.WARNING,"SQLException: ",sqlex);
            return null;
        }
        catch (Exception ex){
            LOGGER.log(Level.WARNING,"Exception: ",ex);
            return null;
        }
    }

    @Override
    public List<Style> findById(int id) {
        List<Style> styles = new ArrayList<Style>();
        //Get connection with DB
        try (Connection conn = getConnection()) {
            //Prepared sql statement
            PreparedStatement st = conn.prepareStatement(
                    "Select * from styles "+
                            "Where id = ?");

            //Set values of parameters
            st.setInt(1, id);
            //Execute query
            ResultSet rs = st.executeQuery();

            Style style = null;
            while (rs.next()) {
                style = new Style(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3));
                styles.add(style);
            }
            rs.close();
            conn.close();
            LOGGER.log(Level.INFO,"Method findById() was executed successfully!");
            return styles;
        }
        catch(SQLException sqlex){
            LOGGER.log(Level.WARNING,"SQLException: ",sqlex);
            return null;
        }
        catch (Exception ex){
            LOGGER.log(Level.WARNING,"Exception: ",ex);
            return null;
        }
    }

    @Override
    public List<Style> findByName(String name) {
        List<Style> styles = new ArrayList<Style>();
        //Get connection with DB
        try (Connection conn = getConnection()) {
            //Prepared sql statement
            PreparedStatement st = conn.prepareStatement(
                    "Select * from styles "+
                            "Where name = ?");

            //Set values of parameters
            st.setString(1, name);
            //Execute query
            ResultSet rs = st.executeQuery();

            Style style = null;
            while (rs.next()) {
                style = new Style(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3));
                styles.add(style);
            }
            rs.close();
            conn.close();
            LOGGER.log(Level.INFO,"Method findByName() was executed successfully!");
            return styles;
        }
        catch(SQLException sqlex){
            LOGGER.log(Level.WARNING,"SQLException: ",sqlex);
            return null;
        }
        catch (Exception ex){
            LOGGER.log(Level.WARNING,"Exception: ",ex);
            return null;
        }
    }

    @Override
    public boolean insert(Style obj){
        //Get connection with DB
        try (Connection conn = getConnection()) {
            //Prepared sql statement
            PreparedStatement st = conn.prepareStatement(
                    "Insert into styles " +
                            "(name, description) " +
                            "values ( ?, ?)");

            //Set values of parameters
            //st.setInt(1, obj.getId());
            st.setString(1, obj.getName());
            st.setString(2, obj.getDescription());

            //Execute query
            st.executeUpdate();

            //Close conn
            conn.close();
        }
        catch(SQLException sqlex){
            LOGGER.log(Level.WARNING,"SQLException: ",sqlex);
            return false;
        }
        catch (Exception ex){
            LOGGER.log(Level.WARNING,"Exception: ",ex);
            return false;
        }
        LOGGER.log(Level.INFO,"Method insert() was executed successfully!");
        return true;
    }

    @Override
    public boolean update(Style obj) {
        //Get connection with DB
        try (Connection conn = getConnection()) {
            //Prepared sql statement
            PreparedStatement st = conn.prepareStatement(
                    "Update styles " +
                            "Set name = ?,description =?" +
                            "Where id = ?");

            //Set values of parameters
            st.setString(1, obj.getName());
            st.setString(2, obj.getDescription());
            st.setInt(3, obj.getId());

            //Execute query
            st.executeUpdate();

            //Close conn
            conn.close();
        }
        catch(SQLException sqlex){
            LOGGER.log(Level.WARNING,"SQLException: ",sqlex);
            return false;
        }
        catch (Exception ex){
            LOGGER.log(Level.WARNING,"Exception: ",ex);
            return false;
        }
        LOGGER.log(Level.INFO,"Method update() was executed successfully!");
        return true;
    }

    @Override
    public boolean delete(Style obj) {
        //Get connection with DB
        try (Connection conn = getConnection()) {
            //Prepared sql statement
            PreparedStatement st = conn.prepareStatement(
                    "Delete from styles " +
                            "Where id = ?");

            //Set values of parameters
            st.setInt(1, obj.getId());

            //Execute query
            st.executeUpdate();

            //Close conn
            conn.close();
        }
        catch(SQLException sqlex){
            LOGGER.log(Level.WARNING,"SQLException: ",sqlex);
            return false;
        }
        catch (Exception ex){
            LOGGER.log(Level.WARNING,"Exception: ",ex);
            return false;
        }
        LOGGER.log(Level.INFO,"Method delete() was executed successfully!");
        return true;
    }
}
