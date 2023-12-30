package ru.skypro.homework.dto;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
public class NewPasswordDTO {
    private String currentPassword;
    private String newPassword;

}
