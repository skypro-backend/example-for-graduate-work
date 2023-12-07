package ru.skypro.homework.dto;

public enum Role {
    ADMIN("admin"),
    USER("user");

    private String role;

    Role(String role) {
        this.role = role;
    }

    public String getValue() {
        return role;
    }


}
