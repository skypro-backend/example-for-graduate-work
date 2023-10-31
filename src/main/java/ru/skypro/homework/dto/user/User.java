package ru.skypro.homework.dto.user;

import lombok.*;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.entity.Users;

import java.util.Optional;

/**
 * Класс-обертка отображаемых данных в профиле пользователя
 * @autor Сулаева Марина
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;
    private String image;

    /**
     * Метод для маппинга из сущности Users в класс-обертку
     * для отображения данных в профиле пользователя
     */
    public static User toUser(Users users) {
        User user = new User();
        user.setId(users.getId());
        user.setEmail(users.getUsername());
        user.setPhone(users.getPhone());
        user.setFirstName(users.getFirstName());
        user.setLastName(users.getLastName());
        user.setRole(users.getRole());
        Optional.ofNullable(users.getImage()).ifPresent(image -> user.setImage(
                "/users/" + users.getImage().getId() + "/image"));
        return user;
    }
}