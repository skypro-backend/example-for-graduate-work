package ru.skypro.homework.dto;

public enum Role {

    USER("USER"), ADMIN("ADMIN");

    private final String code;

    public String getCode() {
        return code;
    }

    Role(String code){
        this.code = code;
    }

}
