package ru.skypro.homework.security;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.entity.Users;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * This class uses in checking for authentication and authorization, implements UserDetails's class
 * @author Sulaeva Marina
 */
@Data
@NoArgsConstructor
public class AdsUserDetails implements UserDetails {

    private Integer id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;

    public AdsUserDetails(Users user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.phone = user.getPhone();
        this.role = user.getRole();
    }

    /**
     * The overridden method for getting list of user's roles
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Optional.ofNullable(role)
                .map(role -> "ROLE_" + role)
                .map(SimpleGrantedAuthority::new)
                .map(List::of)
                .orElse(Collections.emptyList());
    }

    /**
     * the overridden method for getting password
     */
    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     * the overridden method for getting username
     */
    @Override
    public String getUsername() {
        return this.username;
    }

    /**
     * the overridden method to verify the user of an expired account
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * the overridden method to verify the user with unlocked or locked account
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * the overridden method to verify the user of an expired credentials
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * the overridden method to verify the user's account is enabled or not
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
