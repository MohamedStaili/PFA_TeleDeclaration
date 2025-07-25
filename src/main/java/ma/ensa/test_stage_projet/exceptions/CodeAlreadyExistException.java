package ma.ensa.test_stage_projet.exceptions;

public class CodeAlreadyExistException extends RuntimeException {
    public CodeAlreadyExistException(String message) {
        super(message);
    }
}
