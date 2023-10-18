package ru.skypro.homework.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.skypro.homework.entity.AuthUser;

import java.util.Collection;

public class SecurityUserPrincipal implements UserDetails {
    private AuthUser user;

    public SecurityUserPrincipal(AuthUser user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
}
