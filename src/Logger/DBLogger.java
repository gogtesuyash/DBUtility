package Logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;

/*
 * This class will internally call log4j and will just log messages to log file.
 * Log file location is mentioned in log4j.properties
 */
public class DBLogger
{

    static Logger log = Logger.getLogger(DBLogger.class);
    static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    static Calendar cal = Calendar.getInstance();

    static
    {
       // Logger.getRootLogger().addAppender(new LoggerFileAppender("DBChecker.log"));
        // This class is used to redirect messages sent to standard output stream to log file.
        StdOutErrLog.tieSystemOutAndErrToLog();
    }

    public static void debug(String message)
    {
        log.debug(dateFormat.format(cal.getTime()) + "       " + message);
    }

    public static void info(String message)
    {
        log.info(dateFormat.format(cal.getTime()) + "       " + message);
    }
}
