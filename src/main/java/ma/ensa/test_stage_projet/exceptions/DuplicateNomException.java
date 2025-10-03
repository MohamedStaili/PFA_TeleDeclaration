package ma.ensa.test_stage_projet.exceptions;

public class DuplicateNomException extends RuntimeException{
    public DuplicateNomException(String message) {
        super(message);
    }
}
