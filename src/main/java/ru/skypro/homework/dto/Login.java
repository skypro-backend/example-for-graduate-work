package ru.skypro.homework.dto;

import lombok.*;

@Data
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Login {
    private String username;
    private String password;
}
