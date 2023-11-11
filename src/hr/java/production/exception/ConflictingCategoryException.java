package hr.java.production.exception;

/**
 * koristi se kada se unese duplikat kategorije
 */
public class ConflictingCategoryException extends RuntimeException{

    public ConflictingCategoryException(String message) {
        super(message);
    }

    public ConflictingCategoryException(Throwable cause) {
        super(cause);
    }

    public ConflictingCategoryException(String message, Throwable cause) {
        super(message, cause);
    }

}
