package ru.skypro.homework.projection;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Size;

@RequiredArgsConstructor
@Data
public class NewPassword {
    @Size(min = 8, max = 16)
    private final String currentPassword;
    @Size(min = 8, max = 16)
    private final String newPassword;
}
