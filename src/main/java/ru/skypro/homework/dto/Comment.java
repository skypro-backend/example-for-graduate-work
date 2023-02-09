package ru.skypro.homework.dto;


import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
public class Comment {

    /**
     * дата и время создания комментария
     */
    private LocalDateTime createdAt;

    /**
     * id автора
     */
    private Integer author;

    /**
     * id комментария????
     */
    private Integer pk;

    /**
     * Комментарий
     */
    private String text;

}


/**
 * DTO для комментариев
 */
@Getter
@ToString
@AllArgsConstructor
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Comment {
    //Id автора комментария
    int author;
    //Дата создания комментария
    String createdAt;
    //Id объявления
    int pk;
    //Текст комментария
    String text;
}
