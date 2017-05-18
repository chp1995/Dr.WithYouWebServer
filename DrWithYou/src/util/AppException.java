package util;

public class AppException extends Exception{
	
	private String str;

    public AppException(String message)
    {
        str = message;
    }

}
