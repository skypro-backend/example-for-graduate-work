package ru.skypro.homework.dto;

import lombok.*;
import ru.skypro.homework.entity.UserEntity;

import javax.validation.constraints.Email;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private int id;
    @Email
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;
    private String image;

    public static User fromUserEntity(UserEntity userEntity) {
        User user = new User();
        user.setEmail(userEntity.getUsername());
        user.setFirstName(userEntity.getFirstName());
        user.setLastName(userEntity.getLastName());
        user.setPhone(userEntity.getPhone());
        user.setRole(userEntity.getRole());
        user.setImage(userEntity.getImage());
        return user;
    }

    public UserEntity toUser() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(this.getEmail());
        userEntity.setFirstName(this.getFirstName());
        userEntity.setLastName(this.getLastName());
        userEntity.setPhone(this.getPhone());
        userEntity.setRole(this.getRole());
        userEntity.setImage(this.getImage());
        return userEntity;
    }

    public List<Ad> getUserAd() {
        return null;
    }
}
