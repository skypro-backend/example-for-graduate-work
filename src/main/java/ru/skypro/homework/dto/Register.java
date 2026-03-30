package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Data
@Schema(description = "Данные для регистрации пользователя")
public class Register {

    @Schema(description = "логин",
            minLength = 4,
            maxLength = 32,
            example = "john_doe")
    @Size(min = 4, max = 32)

    private String username;
    @Schema(description = "пароль",
            minLength = 8,
            maxLength = 16,
            example = "password123")
    @Size(min = 8, max = 16)
    private String password;

    @Schema(description = "имя пользователя",
            minLength = 2,
            maxLength = 16,
            example = "John")
    @Size(min = 2, max = 16)
    private String firstName;

    @Schema(description = "фамилия пользователя",
            minLength = 2,
            maxLength = 16,
            example = "Doe")
    @Size(min = 2, max = 16)
    private String lastName;

    @Schema(description = "телефон пользователя",
            pattern = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}",
            example = "+7 (123) 456-78-90")
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    private String phone;

    @Schema(description = "роль пользователя",
            allowableValues = {"USER", "ADMIN"},
            example = "USER")
    private Role role;

    public Register() {}

    public Register(String username, String password, String firstName, String lastName, String phone, Role role) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public String getPhone() {
        return phone;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
