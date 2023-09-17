package ru.skypro.homework.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@EqualsAndHashCode
@ToString
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
