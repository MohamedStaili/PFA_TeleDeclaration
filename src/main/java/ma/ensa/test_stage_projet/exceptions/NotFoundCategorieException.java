package ma.ensa.test_stage_projet.exceptions;

public class NotFoundCategorieException extends RuntimeException {
    public NotFoundCategorieException(String message) {
        super(message);
    }
}
