package ru.skypro.homework.dto.request;

import lombok.Data;
import javax.validation.constraints.Size;

@Data
public class NewPasswordRequestDto {
    @Size(min = 8, max = 16)
    private String currentPassword;

    @Size(min = 8, max = 16)
    private String newPassword;
}
