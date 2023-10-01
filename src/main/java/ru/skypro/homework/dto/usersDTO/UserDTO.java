package ru.skypro.homework.dto.usersDTO;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    Integer id;
    String lastName;
    String firstName;
    String email;
    String phone;
    String image;
}
