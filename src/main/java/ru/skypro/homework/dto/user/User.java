package ru.skypro.homework.dto.user;

import lombok.*;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.entity.Users;

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

    public static User toUser(Users users) {
        User user = new User();
        user.setId(users.getId());
        user.setEmail(users.getEmail());
        user.setPhone(users.getPhone());
        user.setFirstName(users.getFirstName());
        user.setLastName(users.getLastName());
        user.setRole(users.getRole());
        user.setImage(users.getImage());
        return user;
    }

    public Users fromUser() {
        Users users = new Users();
        users.setEmail(this.getEmail());
        users.setId(this.getId());
        users.setFirstName(this.getFirstName());
        users.setLastName(this.getLastName());
        users.setPhone(this.getPhone());
        users.setRole(this.getRole());
        users.setImage(this.getImage());
        return users;
    }

}