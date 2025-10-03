package ma.ensa.test_stage_projet.exceptions;

public class NotFoundProfileException extends RuntimeException {
    public NotFoundProfileException(String message) {
        super(message);
    }
}
