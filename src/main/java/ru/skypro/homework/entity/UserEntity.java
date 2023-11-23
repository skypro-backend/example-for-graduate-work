package ru.skypro.homework.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Entity
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @Pattern(regexp = "\\d{10}")  // пример: 1234567890 (10 цифр)
    private String phone;

    @NotBlank
    @Pattern(regexp = "USER|ADMIN")
    private String role;

    private String image;

    @OneToMany(mappedBy = "author")
    private List<AdEntity> ads;

    @OneToMany(mappedBy = "author")
    private List<CommentEntity> comments;

}