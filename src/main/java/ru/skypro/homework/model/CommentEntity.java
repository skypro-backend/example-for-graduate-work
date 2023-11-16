package ru.skypro.homework.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // надо продумать связи в БД
    private UserEntity author;

    private Long createdAt;
    private String text;

}
