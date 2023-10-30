package ru.skypro.homework.entity;

import lombok.*;
import javax.persistence.*;
import ru.skypro.homework.dto.Role;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id"})
@RequiredArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne
    @ToString.Exclude
    @JoinColumn(name = "image_id")
    private Image image;
}
