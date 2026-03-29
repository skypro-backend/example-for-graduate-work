package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "Информация о пользователе")
public class User {

    @Schema(description = "id пользователя",
            example = "1")
    private int id;

    @Schema(description = "логин пользователя",
            example = "john_doe@example.com")
    private String email;

    @Schema(description = "имя пользователя",
            example = "John")
    private String firstName;

    @Schema(description = "фамилия пользователя",
            example = "Doe")
    private String lastName;

    @Schema(description = "телефон пользователя",
            example = "+7 (123) 456-78-90")
    private String phone;

    @Schema(description = "роль пользователя",
            allowableValues = {"USER", "ADMIN"},
            example = "USER")
    private Role role;

    @Schema(description = "ссылка на аватар пользователя",
            example = "/images/avatar.jpg")
    private String image;

    public User(String phone, String lastName, String firstName, String email, int id, String image, Role role) {
        this.phone = phone;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.id = id;
        this.role = role;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstname() {
        return firstName;
    }

    public String getLastname() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public Role getRole() {
        return role;
    }

    public String getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstname) {
        this.firstName = firstname;
    }

    public void setLastName(String lastname) {
        this.lastName = lastname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
