package ru.skypro.homework.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class NewPassword {
    private final String currentPassword;
    private final String newPassword;

}
