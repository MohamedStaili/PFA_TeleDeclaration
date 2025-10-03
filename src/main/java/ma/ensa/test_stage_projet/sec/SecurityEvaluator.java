package ma.ensa.test_stage_projet.sec;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class SecurityEvaluator {

    public boolean isOwnerOrAdmin(String ownerEmail, Authentication auth) {
        boolean isAdmin = auth.getAuthorities()
                .contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return isAdmin || ownerEmail.equals(auth.getName());
    }
}