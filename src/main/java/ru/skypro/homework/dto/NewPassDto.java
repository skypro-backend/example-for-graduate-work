package ru.skypro.homework.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class NewPassDto {
    @NotNull
    private String currentPassword;
    @NotNull
    @Size(min = 8, max = 30)
    private String newPassword;
}

