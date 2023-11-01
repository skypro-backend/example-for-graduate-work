package ru.skypro.homework.dto.user;

import lombok.*;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.entity.Users;

import java.util.Optional;

/**
 * The class-wrapper for outputting data in user's profile
 * @author Sulaeva Marina
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
     * The method for mapping from entity Users to class-wrapper
     * for getting data in user's profile
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