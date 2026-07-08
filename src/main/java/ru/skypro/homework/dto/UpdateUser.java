package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.Size;

@Schema(description = "Редактирование пользователя")
public class UpdateUser {

    @Schema(description = "имя пользователя", minLength = 3, maxLength = 10)
    @Size(min = 3, max = 10)
    private String firstName;

    @Schema(description = "фамилия пользователя", minLength = 3, maxLength = 10)
    @Size(min = 3, max = 10)
    private String lastName;

    @Schema(description = "телефон пользователя", example = "+79000000000")

    private String phone;

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
}