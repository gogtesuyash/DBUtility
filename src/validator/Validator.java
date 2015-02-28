package validator;

import java.io.IOException;
import java.net.InetAddress;

import Logger.DBLogger;
import connector.DBConnector;
import exceptions.DBException;
import exceptions.HostNotReachableException;

/*
 * This class acts as interface, which will take appropriate connector and will call validateConnection So
 * basically it does two functions 1. Check whether host is reachable 2. Establish connection with database
 */
public class Validator
{
    public void validate(DBConnector connector) throws DBException, IOException
    {
        checkHostIsReachable(connector);
        connector.validateConnection();
    }

    private void checkHostIsReachable(DBConnector connector) throws IOException, HostNotReachableException
    {
        String hostName = connector.getHostName();
        DBLogger.info("Checking whether host:[" + hostName + "] is reachable...");
        InetAddress host = InetAddress.getByName(hostName);
        if (host.isReachable(30000))
        {
            DBLogger.debug("Host:[" + hostName + "] is reachable.");
        }
        else
        {
            throw new HostNotReachableException("Host:[" + hostName + "] is not reachable.", 1);
        }

    }
}
