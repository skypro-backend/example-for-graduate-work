package ru.skypro.homework.dto;

import lombok.*;
import org.springframework.lang.Nullable;
import ru.skypro.homework.model.User;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String phone;
    private String mail;
    private String image;



    public static UserDTO fromUser(User user) {
        return new UserDTO(user.getId(), user.getMail(), user.getFirstName(), user.getLastName(), user.getPhone(), user.getImage().getImageName());
    }
}
