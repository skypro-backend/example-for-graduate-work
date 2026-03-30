package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = "Данные для обновления пользователя")
public class UpdateUser {

    @Schema(description = "имя пользователя",
            minLength = 3,
            maxLength = 10,
            example = "John")
    @Size(min = 3, max = 10)
    private String firstName;

    @Schema(description = "фамилия пользователя",
            minLength = 3,
            maxLength = 10,
            example = "Doe")
    @Size(min = 3, max = 10)
    private String lastName;

    @Schema(description = "телефон пользователя",
            pattern = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}",
            example = "+7 (123) 456-78-90")
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    private String phone;

    public UpdateUser() {}

    public UpdateUser(String firstName, String lastName, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastname() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastname(String lastname) {
        this.lastName = lastname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
