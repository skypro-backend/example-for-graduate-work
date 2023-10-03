package ru.skypro.homework.dto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Role {
    USER("USER"),
    ADMIN("ADMIN");
    private final String role;
    public String getStringRole() {
        return role;
    }
}
