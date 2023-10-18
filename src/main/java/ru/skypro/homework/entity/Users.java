package ru.skypro.homework.entity;

import jakarta.validation.constraints.*;
import lombok.*;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;

    private String image;

    @Size(min=4, max=32)
    private String username;

    @Size(min=8, max=16)
    private String password;

    @Size(min=2, max=16)
    private String firstName;

    @Size(min=2, max=16)
    private String lastName;
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    private String phone;

    private Role role;
}