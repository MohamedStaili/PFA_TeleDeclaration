package ma.ensa.test_stage_projet.exceptions;

public class NotFoundUtilisateur extends RuntimeException{
    public NotFoundUtilisateur(String message) {
        super(message);
    }
}
