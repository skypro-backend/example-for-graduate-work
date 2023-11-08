package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class CommentDTO {
    private int author;
    private String authorImage;
    private String authorFirstName;
    private long createdAt;
    private int pk;
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
    createdAt:
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
