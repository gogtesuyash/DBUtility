package connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import Logger.DBLogger;
import exceptions.DBException;

/*
 * This is abstract class ,which is extended by corresponding database connectors. Ex : SqlServerConnector
 * OracleServerConnector This class will establish connection with database
 */
public abstract class DBConnector
{
    protected String connectionString;
    protected String hostName;
    protected int port;
    protected String userName;
    protected String password;
    protected String dbName;

    public DBConnector(String conenctionString, String userName, String password)
    {
        this.connectionString = conenctionString;
        this.userName = userName;
        this.password = password;
    }

    public abstract boolean isValidFormat();

    public abstract void validateConnection() throws DBException;

    public String getHostName()
    {
        return hostName;
    }

    public int getPort()
    {
        return port;
    }

    public String getUserName()
    {
        return userName;
    }

    public String getPassword()
    {
        return password;
    }

    public String getConnectionString()
    {
        return connectionString;
    }

    public String getDBName()
    {
        return dbName;
    }

    public void connect(String driverName) throws DBException, SQLException
    {
        try
        {
            Class.forName(driverName);
        }
        catch (ClassNotFoundException e)
        {
            throw new DBException("Corresponding JDBC driver not found.");
        }

        DBLogger.info("Checking database connection...");

        Connection connection = null;
        connection = DriverManager.getConnection(connectionString, userName, password);
        if (connection != null)
        {
            DBLogger.debug("Connection to database successful.");
            connection.close();
        }

    }
}
