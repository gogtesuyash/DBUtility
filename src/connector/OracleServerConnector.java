package connector;

import java.sql.SQLException;

import exceptions.DBException;
import exceptions.InvalidCredentialsException;
import exceptions.ServiceNotRunningException;

/*
 * This class is used to make connections with ORACLE Server
 */
public class OracleServerConnector extends DBConnector
{
    // driver name used for establishing connection
    private static final String ORACLE_JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";

    public OracleServerConnector(String conenctionString, String userName, String password)
    {
        super(conenctionString, userName, password);
    }

    @Override
    public boolean isValidFormat()
    {
        if (connectionString != null && userName != null && password != null)
        {
            String[] temp = connectionString.split(":");
            if (temp != null && temp.length == 6)
            {
                dbName = temp[5];
                port = Integer.parseInt(temp[4]);
                hostName = temp[3].substring(1);
                return true;
            }

        }
        return false;
    }

    @Override
    public void validateConnection() throws DBException
    {
        try
        {
            connect(ORACLE_JDBC_DRIVER);
        }
        catch (SQLException e)
        {
            if (e.getErrorCode() == 12505 && e.getSQLState().equals("66000"))
            {
                throw new ServiceNotRunningException(
                                                     "Check whether Oracle Server is running and accepting connections at specified port or some problem with firewall configuration",
                                                     2, e);
            }
            if (e.getErrorCode() == 1017 && e.getSQLState().equals("72000"))
            {
                throw new InvalidCredentialsException("Either userName or password is incorrect", 3, e);
            }
            throw new DBException("Connection to database failed with Error." + e.getMessage(), 4, e);

        }
    }

}
