package ru.skypro.homework.user;

import io.swagger.v3.oas.annotations.media.Schema;
import ru.skypro.homework.dto.Role;

@Schema(description = "Информация о пользователе")
public class User {

    @Schema(description = "ID пользователя", example = "1")
    private Integer id;

    @Schema(description = "Логин пользователя", example = "user@example.com")
    private String email;

    @Schema(description = "Имя пользователя", example = "Иван")
    private String firstName;

    @Schema(description = "Фамилия пользователя", example = "Иванов")
    private String lastName;

    @Schema(description = "Телефон пользователя", example = "+79000000000")
    private String phone;

    @Schema(description = "Роль пользователя", example = "пользователь")
    private Role role;

    @Schema(description = "Аватар пользователя", example = "-картинка-")
    private String image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
