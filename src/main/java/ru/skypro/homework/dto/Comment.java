package ru.skypro.homework.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;


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
