package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * сущность Register
 */
@Data
public class Register {
    /**
     * логин
     */
    private String username;
    /**
     * пароль
     */
    private String password;
    /**
     * имя пользователя
     */
    private String firstName;
    /**
     * фамилия пользователя
     */
    private String lastName;
    /**
     * телефон пользователя
     */
    private String phone;
    /**
     * роль пользователя
     */
    private Role role;
}
