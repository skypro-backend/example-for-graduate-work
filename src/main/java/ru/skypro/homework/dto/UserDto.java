package ru.skypro.homework.dto;

import lombok.Data;
import ru.skypro.homework.model.User;

@Data
public class UserDto {
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;
    private String image;


    public UserDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.phone = user.getPhone();
        this.role = user.getRole();
        this.image = user.getImageUrl();
            }
}
