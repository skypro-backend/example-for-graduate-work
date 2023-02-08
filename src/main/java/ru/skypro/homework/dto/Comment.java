package ru.skypro.homework.dto;

import java.time.LocalDate;

public class Comment {

    /**
     * дата создания комментария
     */
    private LocalDate createdAt;

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
