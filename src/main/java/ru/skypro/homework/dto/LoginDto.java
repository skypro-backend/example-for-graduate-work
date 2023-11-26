package ru.skypro.homework.dto;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

    private String username;
    private String password;

}
