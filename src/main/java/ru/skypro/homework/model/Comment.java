package ru.skypro.homework.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "comments")
@Data
public class Comment {

    @Column(name = "id_author")
    private int author;
    @Column(name = "author_Path_Image")
    private String authorImage;
    @Column(name = "author_FirstName")
    private String authorFirstName;
    @Column(name = "time_Create_Comment")
    private long createdAt;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pk;
    @Column(name = "text_Comment")
    private String text;

/*    properties:
    author:
    type: integer
    format: int32
    description: 'id автора комментария'

    authorImage:
    type: string
    description: 'ссылка на аватар автора комментария'

    authorFirstName:
    type: string
    description: 'имя создателя комментария'
    timeComment:
    type: integer
    format: int64
    description: 'дата и время создания комментария в миллисекундах с 00:00:00 01.01.1970'
    pk:
    type: integer
    format: int32
    description: 'id комментария'
    text:
    type: string
    description: 'текст комментария'*/
}
