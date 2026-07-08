package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Size;


@Schema(description = "Регистрация пользователя")
public class Register {

    @Schema(description = "логин", example = "user@example.com")
    @Size(min = 4, max = 32)
    private String username;

    @Schema(description = "пароль", example = "1q2w3e")
    @Size(min = 8, max = 16)
    private String password;

    @Schema(description = "имя пользователя", example = "Иван")
    @Size(min = 2, max = 16)
    private String firstName;

    @Schema(description = "фамилия пользователя", example = "Иванов")
    @Size(min = 2, max = 16)
    private String lastName;

    @Schema(description = "телефон пользователя", example = "+79000000000")
    private String phone;

    @Schema(description = "роль пользователя", example = "пользователь")
    private Role role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}