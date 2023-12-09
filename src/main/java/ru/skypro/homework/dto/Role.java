package ru.skypro.homework.dto;

import org.springframework.security.core.GrantedAuthority;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Роль пользователя")
public enum Role implements GrantedAuthority {
    USER, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }

}
