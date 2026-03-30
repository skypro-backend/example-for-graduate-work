package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Информация о пользователе")
public class User {

    @Schema(description = "id пользователя",
            example = "1")
    private Integer id;  // ✅ Integer вместо int (может быть null)

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

    public User() {
    }

    public User(Integer id, String email, String firstName, String lastName,
                String phone, Role role, String image) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.role = role;
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public void setImage(String image) {
        this.image = image;
    }
}