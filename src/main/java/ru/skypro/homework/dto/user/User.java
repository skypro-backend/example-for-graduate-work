package ru.skypro.homework.dto.user;

import lombok.*;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.entity.Users;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

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
        user.setEmail(users.getUsername());
        user.setPhone(users.getPhone());
        user.setFirstName(users.getFirstName());
        user.setLastName(users.getLastName());
        user.setRole(users.getRole());
        Optional.ofNullable(users.getImage()).ifPresent(image -> user.setImage(
                "/users/" + users.getImage().getId() + "/image"));
        return user;
    }

//    public Users fromUser() {
//        Users users = new Users();
//        users.setUsername(this.getEmail());
//        users.setId(this.getId());
//        users.setFirstName(this.getFirstName());
//        users.setLastName(this.getLastName());
//        users.setPhone(this.getPhone());
//        users.setRole(this.getRole());
//        users.setImage(this.getImage());
//        return users;
//    }


}