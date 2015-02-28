package Logger;

import java.io.PrintStream;

import org.apache.log4j.Logger;

/* 
 * This class will override standard output stream
 * and  will redirect messages to log file
 */
public class StdOutErrLog {

    private static final Logger logger = Logger.getLogger(StdOutErrLog.class);

    public static void tieSystemOutAndErrToLog() {
        System.setOut(createLoggingProxy(System.out));
        System.setErr(createLoggingProxy(System.err));
    }

    public static PrintStream createLoggingProxy(final PrintStream realPrintStream) {
        return new PrintStream(realPrintStream) {
            public void print(final String string) {
                realPrintStream.print(string);
                logger.info(string);
            }
        };
    }
}
