package exceptions;

public class DBException extends Exception
{
    private static final long serialVersionUID = 1L;

    protected int errorCode;

    public DBException(String errorMessage)
    {
        super(errorMessage);
    }

    public DBException(String errorMessage, int errorCode)
    {
        super(errorMessage);
        this.errorCode = errorCode;
    }

    public DBException(String errorMessage, int errorCode, Throwable e)
    {
        super(errorMessage, e);
        this.errorCode = errorCode;
    }

    public int getErrorCode()
    {
        return errorCode;
    }
}
