package ru.skypro.homework.dto.usersDTO;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserDTO {
    String firstName;
    String lastName;
    String phone;
}
