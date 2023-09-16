package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewPassword {

    @NotBlank
    @Size(min = 8, max = 16)
    private String currentPassword;

    @NotBlank
    @Size(min = 8, max = 16)
    private String newPassword;

}
