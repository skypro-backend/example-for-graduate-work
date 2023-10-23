package ru.skypro.homework.dto;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.skypro.homework.pojo.User;

import java.util.Collection;
import java.util.List;


public class UserDetailsDTO implements UserDetails {

private final Long userId;

private final Role userRole;

private final String userName;

    private final String userPassword;

    public UserDetailsDTO(Long userId, Role userRole, String userName, String userPassword) {
        this.userId = userId;
        this.userRole = userRole;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_"+this.userRole.name()));
    }

    @Override
    public String getPassword() {
        return this.userPassword;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    public Long getUserId () {
        return this.userId;
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

    public Role getUserRole() {
        return userRole;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }
}
