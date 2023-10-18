package ru.skypro.homework.service.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


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
    Integer id;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "email")
    String email;

    @Column(name = "phone")
    String phone;

    @Column(name = "username", unique = true)
    String username;

    @Column(name = "password")
    String password;

    @NonNull
    @Enumerated(EnumType.STRING)
    Role role;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<CommentEntity> comments = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private List<AdEntity> ads = new ArrayList<>();

    @OneToOne(targetEntity = ImageEntity.class, fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_image_id")
    private ImageEntity imageEntity;

}