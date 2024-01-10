package ru.skypro.kakavito.dto;

import lombok.Data;

/**
 * Создание ДТО
 */
@Data
public class CommentDTO {
    private Integer author;
    private Integer pk;
    private String authorImage;
    private String authorFirstName;
    private Long createdAt;
    private String text;
}
