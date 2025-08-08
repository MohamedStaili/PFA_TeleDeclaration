package ma.ensa.test_stage_projet.services;

import ma.ensa.test_stage_projet.exceptions.NotFoundUtilisateur;
import ma.ensa.test_stage_projet.exceptions.TokenExperedException;

public interface AccountService {
    void activateUtilisateur(String token ,String Password) throws NotFoundUtilisateur, TokenExperedException;
}
