package ru.skypro.homework.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Setter
@Getter
public class CommentDTO {

    private Integer author;             //id автора комментария
    private String authorImage;     //ссылка на аватар автора комментария
    private String authorFirstName; //имя создателя комментария
    private LocalDateTime createdAt;          //дата и время создания комментария в миллисекундах с 00:00:00 01.01.1970
    private Integer pk;                 //id комментария
    private String text;            //текст комментария
}
