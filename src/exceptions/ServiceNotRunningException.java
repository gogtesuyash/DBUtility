package exceptions;

public class ServiceNotRunningException extends DBException
{
    private static final long serialVersionUID = 1L;

    public ServiceNotRunningException(String errorMsg)
    {
        super(errorMsg);
    }

    public ServiceNotRunningException(String errorMsg, int errorCode)
    {
        super(errorMsg, errorCode);
    }

    public ServiceNotRunningException(String errorMsg, int errorCode, Throwable e)
    {
        super(errorMsg, errorCode, e);
    }
}
