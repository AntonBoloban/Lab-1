package DAO.PostgreSQL;
import DAO.IDAO;
import domain.Clip;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;


import static Connection.PostgreSQLConn.getConnection;
import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

public class ClipDAO implements IDAO<Clip> {


    @Override
    public List<Clip> findAll() {
        List<Clip> clips = new ArrayList<Clip>();
        //Get connection with DB
        try (Connection conn = getConnection()) {
            //Prepared sql statement
            PreparedStatement st = conn.prepareStatement(
                    "Select * from clips ");

            //Execute query
            ResultSet rs = st.executeQuery();

            Clip clip = null;
            while (rs.next()) {
                clip = new Clip(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5));
                clips.add(clip);
            }
            rs.close();
            conn.close();
            LOGGER.log(Level.INFO,"Method findAll() was executed successfully!");
            return clips;
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
    public List<Clip> findById(int id) {
        List<Clip> clips = new ArrayList<Clip>();
        //Get connection with DB
        try (Connection conn = getConnection()) {
            //Prepared sql statement
            PreparedStatement st = conn.prepareStatement(
                    "Select * from clips "+
                        "Where id = ?");
            //Set values of parameters
            st.setInt(1, id);
            //Execute query
            ResultSet rs = st.executeQuery();

            Clip clip = null;
            while (rs.next()) {
                clip = new Clip(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5));
                clips.add(clip);
            }
            rs.close();
            conn.close();
            LOGGER.log(Level.INFO,"Method findById() was executed successfully!");
            return clips;
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
    public List<Clip> findByName(String name) {
        List<Clip> clips = new ArrayList<Clip>();
        //Get connection with DB
        try (Connection conn = getConnection()) {
            //Prepared sql statement
            PreparedStatement st = conn.prepareStatement(
                    "Select * from clips "+
                            "Where name = ?");

            //Set values of parameters
            st.setString(1, name);
            //Execute query
            ResultSet rs = st.executeQuery();

            Clip clip = null;
            while (rs.next()) {
                clip = new Clip(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5));
                clips.add(clip);
            }
            rs.close();
            conn.close();
            LOGGER.log(Level.INFO,"Method findByName() was executed successfully!");
            return clips;
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
    public boolean insert(Clip obj){
        //Get connection with DB
        try (Connection conn = getConnection()) {
            //Prepared sql statement
            PreparedStatement st = conn.prepareStatement(
                    "Insert into clips " +
                            "(name, url, performer_id, style_id) " +
                            "values ( ?, ?, ?, ?)");

            //Set values of parameters
            //st.setInt(1, obj.getId());
            st.setString(1, obj.getName());
            st.setString(2, obj.getUrl());
            st.setInt(3, obj.getPerformer_id());
            st.setInt(4, obj.getStyle_id());

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
    public boolean update(Clip obj) {
        //Get connection with DB
        try (Connection conn = getConnection()) {
            //Prepared sql statement
            PreparedStatement st = conn.prepareStatement(
                    "Update clips " +
                            "Set name = ?, url =?, performer_id = ?, style_id = ? " +
                            "Where id = ?");

            //Set values of parameters
            st.setString(1, obj.getName());
            st.setString(2, obj.getUrl());
            st.setInt(3, obj.getPerformer_id());
            st.setInt(4, obj.getStyle_id());
            st.setInt(5, obj.getId());

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
    public boolean delete(Clip obj) {
        //Get connection with DB
        try (Connection conn = getConnection()) {
            //Prepared sql statement
            PreparedStatement st = conn.prepareStatement(
                    "Delete from clips " +
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
