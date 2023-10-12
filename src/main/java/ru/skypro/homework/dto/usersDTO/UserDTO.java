package ru.skypro.homework.dto.usersDTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class UserDTO {
    Integer id;
    String lastName;
    String firstName;
    String email;
    String phone;
    String image;
}

//Пример того, как работает коммит в форк