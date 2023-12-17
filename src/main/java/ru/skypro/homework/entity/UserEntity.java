package ru.skypro.homework.entity;

import lombok.*;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;
import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "user_entity")

public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pk;

    @Email
    @Column(nullable = false, length = 32)
    private String email;

    @Column(name = "first_name", nullable = false, length = 16)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 16)
    private String lastName;

    @Pattern(regexp = "\\+7\\s?\\(?(\\d{3})\\)?\\s?(\\d{3}-?\\d{2}-?\\d{2})")
    @Column(name = "phone", nullable = false, length = 12)
    private String phone;

    @Column(name = "password", nullable = false, length = 60)
    private String password;

    @NotBlank
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "image_entity_id")
    private ImageEntity imageEntity;

    @OneToMany(mappedBy = "author")
    private List<AdEntity> ads;

    @OneToMany(mappedBy = "author")
    private List<CommentEntity> comments;

}