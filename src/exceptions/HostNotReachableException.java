package exceptions;

public class HostNotReachableException extends DBException
{
    private static final long serialVersionUID = 1L;

    public HostNotReachableException(String errorMessage)
    {
        super(errorMessage);
    }

    public HostNotReachableException(String errorMessage, int errorCode)
    {
        super(errorMessage, errorCode);
    }
}
