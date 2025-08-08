package ma.ensa.test_stage_projet.sec;

import lombok.RequiredArgsConstructor;
import ma.ensa.test_stage_projet.entities.Utilisateur;
import ma.ensa.test_stage_projet.exceptions.NotFoundUtilisateur;
import ma.ensa.test_stage_projet.repositories.UtilisateurRepository;
import ma.ensa.test_stage_projet.services.AdminService;
import ma.ensa.test_stage_projet.services.UtilisateurService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserService implements UserDetailsService {
    private final UtilisateurRepository utilisateurRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email);
        if (utilisateur == null) throw new UsernameNotFoundException("not found user with email: " + email);
        return new UserDetailsInfo(utilisateur);
    }
}
