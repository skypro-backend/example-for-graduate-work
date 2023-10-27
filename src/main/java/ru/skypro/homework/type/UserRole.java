package ru.skypro.homework.type;

public enum UserRole {

    ADMIN("administrator"),
    USER("user");
    private final String title;

    UserRole(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }
}
