package ru.skypro.homework.security;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import ru.skypro.homework.dto.SecurityUserDto;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Component
public class MyUserDetails implements UserDetails {
    private SecurityUserDto securityUserDto;

    public void setUserDto(SecurityUserDto userDto) {
        this.securityUserDto = userDto;
    }

    public Integer getIdUserDto() {
        return Optional.ofNullable(securityUserDto)
                .map(SecurityUserDto::getId)
                .orElse(null);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Optional.ofNullable(securityUserDto)
                .map(SecurityUserDto::getRole)
                .map(role -> "ROLE_" + role)
                .map(SimpleGrantedAuthority::new)
                .map(Collections::singleton)
                .orElseGet(Collections::emptySet);
    }

    @Override
    public String getPassword() {
        return Optional.ofNullable(securityUserDto)
                .map(SecurityUserDto::getPassword)
                .orElse(null);
    }

    @Override
    public String getUsername() {
        return Optional.ofNullable(securityUserDto)
                .map(SecurityUserDto::getEmail)
                .orElse(null);
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
