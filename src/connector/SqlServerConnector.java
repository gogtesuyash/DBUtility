package connector;

import java.sql.SQLException;

import exceptions.DBException;
import exceptions.InvalidCredentialsException;
import exceptions.ServiceNotRunningException;

/*
 * This class is used to make conenctions with SQL Server
 */
public class SqlServerConnector extends DBConnector
{

    private static final String SQLSERVER_JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    public SqlServerConnector(String conenctionString, String userName, String password)
    {
        super(conenctionString, userName, password);
    }

    @Override
    public boolean isValidFormat()
    {
        if (connectionString != null && userName != null && password != null)
        {
            int index = -1;
            if ((index = connectionString.indexOf("databaseName")) > 0)
            {
                if (connectionString.substring(index).split("=").length == 2)
                {
                    dbName = connectionString.substring(index).split("=")[1];
                    String[] temp = null;
                    temp = connectionString.substring(0, index - 1).split("//");
                    if (temp != null && temp.length == 2 && temp[1].split(":").length == 2)
                    {
                        temp = temp[1].split(":");
                        hostName = temp[0];
                        port = Integer.parseInt(temp[1]);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void validateConnection() throws DBException
    {
        try
        {
            connect(SQLSERVER_JDBC_DRIVER);
        }
        catch (SQLException e)
        {
            if (e.getErrorCode() == 18456 && e.getSQLState().equals("S0001"))
            {
                throw new InvalidCredentialsException("Either userName or password is incorrect", 3, e);
            }
            else if (e.getErrorCode() == 0 && e.getSQLState().equals("08S01"))
            {
                throw new ServiceNotRunningException(
                                                     "Check whether SQL Server is running and accepting connections at specified port or some problem with firewall configuration",
                                                     2, e);
            }
            throw new DBException("Connection to database failed with Error." + e.getMessage(), 4, e);
        }
    }

}
