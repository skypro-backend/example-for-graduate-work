package ru.skypro.homework.entity;

import lombok.Data;

import javax.persistence.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "author")
    @NotNull
    private UserEntity author;

    @NotBlank
    private String authorImage;

    @NotBlank
    private String authorFirstName;

    @NotNull
    private long createdAt;

    @NotBlank
    @Size(max = 255)
    private String text;

}