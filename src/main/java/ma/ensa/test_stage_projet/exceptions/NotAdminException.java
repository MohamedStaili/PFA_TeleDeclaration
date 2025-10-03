package ma.ensa.test_stage_projet.exceptions;

public class NotAdminException extends RuntimeException {
    public NotAdminException(String message) {
        super(message);
    }
}
