package ru.skypro.homework.projection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.skypro.homework.dto.Role;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserView {
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;
    private String image;
}
