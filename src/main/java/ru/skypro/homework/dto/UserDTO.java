package ru.skypro.homework.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;
import ru.skypro.homework.model.User;

@Getter
@NoArgsConstructor
public class UserDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String phone;
    private String mail;
//    private String image;

    public UserDTO (int id, String mail, String firstName, String lastName, String phone) {
        this.id = id;
        this.mail = mail;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public static UserDTO fromUser(User user) {
        return new UserDTO(user.getId(), user.getMail(), user.getFirstName(), user.getLastName(), user.getPhone() );
    }
}
