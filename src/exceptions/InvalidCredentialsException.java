package exceptions;

public class InvalidCredentialsException extends DBException
{
    private static final long serialVersionUID = 1L;

    public InvalidCredentialsException(String msg)
    {
        super(msg);
    }
    
    public InvalidCredentialsException(String msg,int errorCode)
    {
        super(msg,errorCode);
    }
    
    public InvalidCredentialsException(String msg,int errorCode,Throwable e)
    {
        super(msg,errorCode,e);
    }
}
