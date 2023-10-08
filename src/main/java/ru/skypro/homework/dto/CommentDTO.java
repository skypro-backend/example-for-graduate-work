package ru.skypro.homework.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class CommentDTO {
    private Integer author;
    //ссылка на аватар автора комментария
    private String authorImage;
    private String authorFirstName;
    //дата и время создания комментария в миллисекундах с 00:00:00 01.01.1970 -  LocalDataTime???
    private LocalDateTime createdAt;
    //id комментария
    private Integer pk;
    private String text;
}
