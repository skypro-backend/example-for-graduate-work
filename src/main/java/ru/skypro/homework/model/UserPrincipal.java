package ru.skypro.homework.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.skypro.homework.dto.UserDetailsDto;

import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {

    private final UserDetailsDto userDetailsDto;

    public UserPrincipal(UserDetailsDto userDetailsDto) {
        this.userDetailsDto = userDetailsDto;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority simpleGrantedAuthority= new SimpleGrantedAuthority("ROLE_"+userDetailsDto.getRole().name());
        return List.of(simpleGrantedAuthority);
    }

    @Override
    public String getPassword() {
        return userDetailsDto.getPassword();
    }

    @Override
    public String getUsername() {
        return userDetailsDto.getUsername();
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
