package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.skypro.homework.entity.User;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class UserDto {
    private Integer id; // ($int32)
    private String email; //логин пользователя
    private String firstName; //имя пользователя
    private String lastName; //фамилия пользователя
    private String phone; //телефон пользователя
    private String role; //роль пользователя
    private String image; // ссылка на аватар пользователя

    public static UserDto fromUser(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setEmail(user.getUsername());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setPhone(user.getPhone());
        dto.setRole(user.getRole());
        if (user.getImage() != null) {
            dto.setImage(String.format("/users/image/%s", user.getImage()));
        } else {
            dto.setImage(null);
        }

        return dto;
    }


    public User toUser() {
        User user = new User();
        user.setId(this.getId());
        user.setUsername(this.getEmail());
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
