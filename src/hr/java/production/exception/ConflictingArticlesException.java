package hr.java.production.exception;

/**
 * koristi se kada se u istu tvornicu ili trgovinu unese duplikat artikla
 */
public class ConflictingArticlesException extends Exception{

    public ConflictingArticlesException(String message){
        super(message);
    }
    public ConflictingArticlesException(Throwable cause) {
        super(cause);
    }
    public ConflictingArticlesException(String message, Throwable cause) {
        super(message, cause);
    }
}
