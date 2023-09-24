package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class UserDto {
    private Integer id; // ($int32)
    private String username; //логин пользователя
    private String password; //пароль пользователя
    private String firstName; //имя пользователя
    private String lastName; //фамилия пользователя
    private String phone; //телефон пользователя
    private String role; //роль пользователя
    private String image; // ссылка на аватар пользователя

    public static UserDto fromUser(UserDto user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setPhone(user.getPhone());
        dto.setRole(user.getRole().toString());
        dto.setImage(user.getImage());

        return dto;
    }


    public UserDto toUser() {
        UserDto user = new UserDto();
        user.setId(this.getId());
        user.setUsername(this.getUsername());
        user.setPassword(user.getPassword());
        user.setFirstName(this.getFirstName());
        user.setLastName(this.getLastName());
        user.setPhone(this.getPhone());
        if (this.getRole() == null) {
            user.setRole(Role.USER.name());
        } else {
            user.setRole(this.getRole());
        }
        user.setImage(this.getImage());

        return user;
    }
}
