package ru.skypro.homework.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.entity.Users;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersDTO {
    private Integer id;
    private String image;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;

    public static UsersDTO fromUsers(Users users) {
        UsersDTO usersDTO = new UsersDTO();
        usersDTO.setId(users.getId());
        usersDTO.setPassword(users.getPassword());
        usersDTO.setUsername(users.getUsername());
        usersDTO.setPhone(users.getPhone());
        usersDTO.setFirstName(users.getFirstName());
        usersDTO.setLastName(users.getLastName());
        usersDTO.setRole(users.getRole());
        usersDTO.setImage(users.getImage());
        return usersDTO;
    }

    public Users toUsers() {
        Users users = new Users();
        users.setUsername(this.getUsername());
        users.setPassword(this.getPassword());
        users.setId(this.getId());
        users.setFirstName(this.getFirstName());
        users.setLastName(this.getLastName());
        users.setPhone(this.getPhone());
        users.setRole(this.getRole());
        users.setImage(this.getImage());
        return users;
    }
}
