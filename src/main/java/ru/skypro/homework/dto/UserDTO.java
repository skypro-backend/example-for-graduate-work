package ru.skypro.homework.dto;

import lombok.Data;
import ru.skypro.homework.pojo.Avatar;

@Data
public class UserDTO {

    private Long userID;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Role role;
    private Avatar avatar;
    private String password;

    public UserDTO() {
    }
}
