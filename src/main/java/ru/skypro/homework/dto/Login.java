package ru.skypro.homework.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Login {

    @NotBlank
    @Size(min = 4, max = 32)
    @Email
    private String username;

    @NotBlank
    @Size(min = 8, max = 16)
    private String password;
}
