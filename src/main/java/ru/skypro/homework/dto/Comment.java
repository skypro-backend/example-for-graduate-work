package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode
@Data
public class Comment {
    private Integer author; // ($int32) id автороа комментария
    private String authorImage; // ссылка на аватар автора комментария
    private String authorFirstName; // имя создателя комментария
    private Integer createdAt; //($int32) дата и время создания комментария в миллисекундах с 00:00:00 01.01.1970
    private Integer pk; //($int32) id комментария
    private String text; // текст комментария
}
