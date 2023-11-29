package ru.skypro.homework.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private Integer authorId;
    private String authorImage;
    private String authorFirstName;
    private Long createdAt;
    private Integer pkId;
    private String text;
}
