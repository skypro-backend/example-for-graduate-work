package ru.skypro.homework.dto.usersDTO;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewPasswordDTO {
    String currentPassword;
    String newPassword;
}