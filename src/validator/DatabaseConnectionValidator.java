package validator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import Logger.DBLogger;
import connector.DBConnector;
import connector.DBConnectorFactory;
import exceptions.DBException;

/*
 * This class is starting point from where execution will start. 
 * It takes 3 parameters as input 
 * 1.ConnectionString 
 * 2. Username 
 * 3. Password Based on connectionString passed,it will validate it and get
 * corresponding connector to make connections
 */
public class DatabaseConnectionValidator
{

    public static void main(String[] args)
    {
        
        if (args.length != 5)
        {
            writeFileLocationToLogFile(args[3], args[4]);
            DBLogger.debug("Invalid number of input parameters");
            return;
        }
        String connectionString = args[0];
        String userName = args[1];
        String password = args[2];
        writeFileLocationToLogFile(args[3], args[4]);
        DBLogger.info("Execution Started");
        try
        {
            DBConnector connector = DBConnectorFactory.getDBConnector(connectionString, userName, password);

            if (connector.isValidFormat())
            {
                Validator dbValidator = new Validator();
                dbValidator.validate(connector);
            }
            else
                throw new DBException("Invalid connectionString");
        }
        catch (DBException e)
        {
            handleExceptions(e.getMessage(), e.getErrorCode(), e);
        }
        catch (Exception e)
        {
            handleExceptions("Internal error occurred,failed to connect database ", 4, e);
        }
        System.exit(0);
    }

    public static void handleExceptions(String errorMsg, int exitCode, Throwable e)
    {
        DBLogger.debug(errorMsg);
        DBLogger.debug("Connection to database failed.");
        if (e != null)
            e.printStackTrace();
        System.exit(exitCode);
    }

    public static void writeFileLocationToLogFile(String fileLoc, String installedLoc)
    {
        try
        {
            fileLoc = fileLoc.replace("\\", "/");
            fileLoc = fileLoc + "/log4j.properties";
            String[] parts = null;
            installedLoc = installedLoc.replace("\\", "/");
            parts = installedLoc.split("/");
            installedLoc = "";
            if (parts != null && parts.length > 0)
            {
                int len = parts.length;
                int i = 0;
                while (i < len)
                {
                    installedLoc = installedLoc + parts[i];
                    installedLoc = installedLoc + "/";

                    if(parts[i].indexOf("bladelogic") >=0 || parts[i].indexOf("BladeLogic") >=0)
                        break;
                    i++;
                    
                }
            }
            installedLoc = installedLoc + "Logs";
            File file = new File(fileLoc);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = "", oldtext = "";
            while ((line = reader.readLine()) != null)
            {
                oldtext += line + "";
                oldtext += "\n";
            }
            reader.close();
            String newtext = oldtext.replace("${log}", installedLoc);

            FileWriter writer = new FileWriter(fileLoc);

            writer.write(newtext);
            writer.close();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
            System.exit(4);
        }
    }
}
