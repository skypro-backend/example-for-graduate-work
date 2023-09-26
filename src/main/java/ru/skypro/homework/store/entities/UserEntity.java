package ru.skypro.homework.store.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "last_name", nullable = false)
    String lastName;

    @Column(name = "first_name", nullable = false)
    String firstName;

    @Column(name = "email", nullable = false)
    String email;

    @Column(name = "phone", nullable = false)
    String phone;

    @Column(name = "image", nullable = false)
    String image;

    @Column(name = "username", nullable = false)
    String username;

    @Column(name = "password", nullable = false)
    String password;

    @NonNull
    @Enumerated(EnumType.STRING)
    Role role;

    @OneToMany
    @JoinColumn(name = "")

    public static UserEntity makeDefaults(
            String lastName,
            String firstName,
            String email,
            String phone,
            String image,
            String username,
            String password,
            Role role) {
        return builder()
                .lastName(lastName)
                .firstName(firstName)
                .email(email)
                .phone(phone)
                .image(image)
                .username(username)
                .password(password)
                .role(role)
                .build();
    }
}
