package ru.skypro.homework.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Пользователь приложения.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "app_user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "username",
            nullable = false,
            unique = true,
            length = 32
    )
    private String username;

    @Column(
            name = "password",
            nullable = false
    )
    private String password;

    @Column(
            name = "first_name",
            nullable = false,
            length = 16
    )
    private String firstName;

    @Column(
            name = "last_name",
            nullable = false,
            length = 16
    )
    private String lastName;

    @Column(
            name = "phone",
            nullable = false,
            length = 32
    )
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "role",
            nullable = false,
            length = 20
    )
    private Role role;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "image", columnDefinition = "bytea")
    private byte[] image;

    @Column(
            name = "image_media_type",
            length = 100
    )
    private String imageMediaType;

    @OneToMany(
            mappedBy = "author",
            fetch = FetchType.LAZY
    )
    private List<AdEntity> ads = new ArrayList<>();

    @OneToMany(
            mappedBy = "author",
            fetch = FetchType.LAZY
    )
    private List<CommentEntity> comments = new ArrayList<>();
}
