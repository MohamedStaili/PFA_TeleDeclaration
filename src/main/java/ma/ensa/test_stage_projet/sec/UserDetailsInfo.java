package ma.ensa.test_stage_projet.sec;

import ma.ensa.test_stage_projet.entities.Utilisateur;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserDetailsInfo implements UserDetails {
    private String email ;
    private String password ;
    private GrantedAuthority authorities;
    public UserDetailsInfo(Utilisateur appUser) {
        this.email = appUser.getEmail();
        this.password = appUser.getPassword();
        this.authorities = new SimpleGrantedAuthority(appUser.getProfile().getNom());
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(authorities);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
