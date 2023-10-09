package ru.skypro.homework.dto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Role {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");
    private final String role;
    public String getStringRole() {
        return role;
    }
}
