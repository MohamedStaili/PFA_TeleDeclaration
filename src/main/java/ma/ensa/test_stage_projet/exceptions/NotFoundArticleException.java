package ma.ensa.test_stage_projet.exceptions;

public class NotFoundArticleException extends RuntimeException {
    public NotFoundArticleException(String message) {
        super(message);
    }
}
