package ru.skypro.homework.dto;

import org.springframework.security.core.GrantedAuthority;

public enum RoleDto implements GrantedAuthority {
    USER,
    ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
