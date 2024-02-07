package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class CommentDTO {
    /**
     * id автора комментария
     */
    Integer id;
    /**
     * ссылка на аватар автора комментария
     */
    String authorImage;
    /**
     * имя создателя комментария
     */
    String authorFirstName;
    /**
     * дата и время создания комментария в милисекундах с 00:00:00 01.01.1970
     */
    Integer createAt;
    /**
     * id комментария
     */
    Integer pk;
    /**
     * текст комментария
     */
    String text;
}
