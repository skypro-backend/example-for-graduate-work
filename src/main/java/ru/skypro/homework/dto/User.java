package ru.skypro.homework.dto;

import lombok.*;
import org.springframework.http.ResponseEntity;
import ru.skypro.homework.repository.UserRepository;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class User {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;
}
