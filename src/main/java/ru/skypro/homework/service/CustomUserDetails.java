package ru.skypro.homework.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.skypro.homework.dto.authentication.ExtendedLoginViaDB;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails  implements UserDetails {

    private final ExtendedLoginViaDB extendedLoginViaDB;

    public CustomUserDetails(ExtendedLoginViaDB extendedLoginViaDB) {
        this.extendedLoginViaDB = extendedLoginViaDB;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(new String("ROLE_") + extendedLoginViaDB.getRole().toString()));
    }

    @Override
    public String getPassword() {
        return extendedLoginViaDB.getPassword();
    }

    @Override
    public String getUsername() {
        return extendedLoginViaDB.getUsername();
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
