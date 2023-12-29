package ru.skypro.homework.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.skypro.homework.model.User;

import java.util.Collection;
import java.util.Collections;

/**
 * <h2>JavaKidsUserDetails</h2><br>
 * <b>Methods of parent interface {@link UserDetails }</b><br>
 * Collection<? extends GrantedAuthority> getAuthorities();<br>
 * String getPassword();<br>
 * String getUsername();<br>
 * boolean isAccountNonExpired();<br>
 * boolean isAccountNonLocked();<br>
 * boolean isCredentialsNonExpired();<br>
 * boolean isEnabled();<br>
 */
@RequiredArgsConstructor
public class JavaKidsUserDetails implements UserDetails {

    private User user;

    JavaKidsUserDetails(User user) {
        this.user = user;
    }


    /**
     * <h2>getAuthorities</h2>
     *
     * @return user role
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getUserRole()));
    }

    /**
     * <h2>getPassword</h2>
     *
     * @return user password
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * @return
     */
    @Override
    public String getUsername() {
        return user.getEmail();
    }

    /**
     * Not used in current version
     * @return always true
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Not used in current version
     * @return always true
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Not used in current version
     * @return always true
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Not used in current version
     * @return always true
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
