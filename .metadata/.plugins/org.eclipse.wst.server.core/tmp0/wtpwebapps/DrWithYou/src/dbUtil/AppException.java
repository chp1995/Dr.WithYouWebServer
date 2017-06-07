package dbUtil;

/**
 * Created by jh on 2017/4/12.
 */
public class AppException extends Exception{

    private String str;

    public AppException(String message)
    {
        str = message;
    }
}
