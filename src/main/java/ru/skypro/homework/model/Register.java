package ru.skypro.homework.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Entity(name = "registers")
@RequiredArgsConstructor

public class Register {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 4, max = 32)
    private String username;

    @Size(min = 8, max = 16)
    private String password;

    @Size(min = 8, max = 16)
    private String firstName;

    @Size(min = 8, max = 16)
    private String lastName;
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    private String phone;
    @Enumerated(EnumType.STRING)
    private Role role;
}
