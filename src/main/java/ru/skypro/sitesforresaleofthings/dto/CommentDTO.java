package ru.skypro.sitesforresaleofthings.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * DTO комментария
 */

/**
 * Свойства:
 * 1) author - id автора комментария,
 * 2) authorImage - ссылка на аватар автора комментария,
 * 3) authorFirstName - имя создателя комментария,
 * 4) createdAt - дата и время создания комментария в миллисекундах с 00:00:00 01.01.1970,
 * 5) pk - id комментария,
 * 6) text - текст комментария
 */
@Data
public class CommentDTO {

    private Integer author;
    private String authorImage;
    private String authorFirstName;
    private LocalDateTime createdAt;
    private Integer pk;
    private String text;
}