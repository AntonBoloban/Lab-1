package Connection;

import DBConf.PostgreSql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

public class PostgreSQLConn {


    public static Connection getConnection() throws Exception {
        System.out.println("-------- PostgreSQL "
                + "JDBC Connection Testing ------------");

        try {

            Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException e) {

            //System.out.println("Where is your PostgreSQL JDBC Driver? "
               //     + "Include in your library path!");
            //e.printStackTrace();
            LOGGER.log(Level.WARNING, "Where is your PostgreSQL JDBC Driver? \"\n" +
                    "                    + \"Include in your library path!", e);
            return null;
        }

        LOGGER.log(Level.INFO,"PostgreSQL JDBC Driver Registered!");

        Connection connection = null;

        try {

            connection = DriverManager.getConnection(
                    PostgreSql.getUrl(),
                    PostgreSql.getOwner(),
                    PostgreSql.getRoot());

        } catch (SQLException e) {

            LOGGER.log(Level.WARNING, "Connection Failed! Check output console", e);
            return null;

        }

        if (connection != null) {
            LOGGER.log(Level.INFO,"You made it, take control your database now!");
            return connection;
        } else {
            LOGGER.log(Level.WARNING,"Failed to make connection!");
            return null;
        }
    }
}
