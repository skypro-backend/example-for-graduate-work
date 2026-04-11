package ru.skypro.homework.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.persistence.*;
import java.util.List;

/**
 * Сущность пользователя, включающая идентификатор, email, имя, фамилию,
 * телефон, изображение, пароль, статус активности, список объявлений и комментариев,
 * а также роль.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone")
    private String phone;
    @Column(name = "image")
    private String image;
    @Column(name = "password")
    private String password;

    @Column(name = "enabled", nullable = false)
    private Boolean enabled = true;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<AdvertisementEntity> ads;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<CommentEntity> comments;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;
}
