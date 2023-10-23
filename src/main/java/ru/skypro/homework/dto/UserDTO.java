package ru.skypro.homework.dto;

import lombok.Data;
import ru.skypro.homework.pojo.Image;

@Data
public class UserDTO {

    private Long userID;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Role role;
    private Image image;
    private String password;

    public UserDTO() {
    }
}
