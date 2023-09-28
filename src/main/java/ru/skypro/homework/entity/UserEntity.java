package ru.skypro.homework.entity;

import lombok.*;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Это поле не может быть пустым")
    @Size(min = 4, max = 32, message = "Логин должен содержать от 4 до 32 символов")
    @Email
    private String username;

    @Column(name = "first_name")
    @NotBlank(message = "Это поле не может быть пустым")
    @Size(min = 2, max = 16, message = "Имя пользователя должно содержать не менее 2 и не более 16 символов")
    private String firstName;

    @Column(name = "last_name")
    @NotBlank(message = "Это поле не может быть пустым")
    @Size(min = 2, max = 16, message = "Фамилия пользователя должна содержать не менее 2 и не более 16 символов")
    private String lastName;

    @NotBlank(message = "Это поле не может быть пустым")
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}",
            message = "Введите номер в формате +7 (000) 000-00-00")
    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String image;

    @NotBlank(message = "Это поле не может быть пустым")
    private String password;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AdEntity> ads;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentEntity> comments;

}