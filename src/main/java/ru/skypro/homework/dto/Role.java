package ru.skypro.homework.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

//@Getter
//@RequiredArgsConstructor

public enum Role {
    USER(Set.of(Permission.BASIC)),
    ADMIN(Set.of(Permission.BASIC, Permission.DELETE_ANY_AD, Permission.DELETE_ANY_COMMENT,
            Permission.UPDATE_ANY_AD, Permission.UPDATE_ANY_COMMENT));

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    private final Set<Permission> permissions;

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
                .collect(Collectors.toSet());
    }
}
