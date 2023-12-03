package ru.skypro.homework.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private int author; // id автора комментария
    private String authorImage;
    private String authorFirstName;
    private Long createdAt;
    private Integer pk; // id комментария
    private String text;
}