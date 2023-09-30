package ru.skypro.homework.security;

import org.springframework.security.core.userdetails.UserDetails;
import ru.skypro.homework.entity.User;

import java.util.ArrayList;
import java.util.List;


public class SecurityUserPrincipal implements UserDetails {
    private User user;

    public SecurityUserPrincipal(User user) {
        this.user = user;
    }

    @Override
    public ArrayList getAuthorities() {
        return new ArrayList<>(List.of(user.getRole()));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
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
