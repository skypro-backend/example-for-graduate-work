package ru.skypro.homework.dto.usersDTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class NewPasswordDTO {
    String currentPassword;
    String newPassword;
}