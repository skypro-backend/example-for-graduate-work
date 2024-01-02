package ru.skypro.homework.dto.users;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewPasswordDto {

    private String currentPassword;
    private String newPassword;
}
