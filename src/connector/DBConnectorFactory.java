package connector;

/*
 * This class will take conenctionString as input and will return corresponding 
 * connector, which will be used to connection.
 * 
 */
public class DBConnectorFactory
{
    public static DBConnector getDBConnector(String connectionString, String userName, String password)
    {
        DBConnector connector = null;
        if (connectionString.indexOf("sqlserver") >= 0)
        {
            connector = new SqlServerConnector(connectionString, userName, password);
        }
        else if (connectionString.indexOf("oracle") >= 0)
        {
            connector = new OracleServerConnector(connectionString, userName, password);
        }
        return connector;
    }
}
